package com.axonivy.utils.gdprconnector.utils;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EPOCH_DATE_FORMAT;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import com.axonivy.connector.successfactors.connector.rest.SFODataUpsertResult;
import com.axonivy.utils.gdprconnector.enums.IPeoplePicklistId;
import com.axonivy.utils.gdprconnector.enums.LegalEntityEnum;
import com.axonivy.utils.gdprconnector.persistence.entities.RequestResponse;
import com.axonivy.utils.gdprconnector.service.IPeopleJobService;
import com.axonivy.utils.gdprconnector.service.IPeoplePickListValueV2Service;
import com.axonivy.utils.gdprconnector.service.IPeopleUpsertService;
import com.axonivy.utils.gdprconnector.service.IPeopleUserService;
import com.axonivy.utils.gdprconnector.service.LegalEntityService;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.bo.IPeoplePerson;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;
import com.axonivy.utils.gdprconnector.constant.IPeopleConstants;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;

public class IPeopleUtils {
	private static final String UPSERT_RESULT_NOTIFICATION = "%s - %s::Changing of %s executed with status: %s - %s, message: %s";

	private IPeopleUtils() {
	}

	public static FoObject findLegalEntityOfLoginUser() {
		String email = getEmailAddressOfIvyUser(Ivy.session().getSessionUser());
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		return findLegalEntityByEmail(email);
	}

	public static LegalEntityEnum findLegalEntityByCaseCreator() {
		return Optional.ofNullable(Ivy.wfCase()).map(ICase::getCreatorUser).map(IPeopleUtils::getEmailAddressOfIvyUser)
				.map(IPeopleUtils::findLegalEntityEnumByEmail).orElse(null);
	};

	public static String getEmailAddressOfIvyUser(IUser user) {
		return ObjectUtils.isEmpty(user) ? StringUtils.EMPTY : Sudo.get(() -> user.getEMailAddress());
	}

	public static LegalEntityEnum findLegalEntityEnumByEmail(String email) {
		return Optional.ofNullable(findEmployeeCompanyByEmail(email)).map(FoObject::getExternalCode)
				.map(LegalEntityEnum::valueOfLegalEntityId).orElse(null);
	}

	public static FoObject findLegalEntityByEmail(String email) {
		FoObject result = null;
		FoObject employeeCompany = findEmployeeCompanyByEmail(email);
		if (employeeCompany != null) {
			result = LegalEntityService.findById(employeeCompany.getExternalCode());
		}
		return result;
	}

	public static FoObject findEmployeeCompanyByEmail(String email) {
		SubProcessCallResult callResult = SubProcessCall.withPath("IPeople/IPeoplePerson")
				.withStartSignature("getOperatingCompany(String)").withParam("email", email).call();
		FoObject employeeCompany = (FoObject) callResult.get("company");
		return employeeCompany;
	}

	public static FoObject findEmployeeCompanyByEmail(IPeoplePerson iPeoplePerson) {
		return findEmployeeCompanyByEmail(Optional.ofNullable(iPeoplePerson).map(IPeoplePerson::getEmail).orElse(""));
	}

	public static Boolean isNoneBlankFoObject(FoObject foObject) {
		return Objects.nonNull(foObject) && StringUtils.isNoneBlank(foObject.getName());
	}

	public static Boolean isNoneBlankIPeopleUser(IPeopleUser user) {
		return Objects.nonNull(user) && StringUtils.isNoneBlank(user.getIdentityNumber());
	}

	public static Boolean isUpsertedSucceed(int statusCode) {
		return statusCode == HttpStatus.SC_CREATED || statusCode == HttpStatus.SC_OK
				|| statusCode == HttpStatus.SC_NO_CONTENT;
	}

	public static Boolean isUpsertedSucceed(RequestResponse response) {
		return Objects.nonNull(response) && isUpsertedSucceed(response.getHttpCode());
	}

	public static RequestResponse handleUpsertResponseResultMessage(SFODataUpsertResult upsertResult,
			String processName, String objectName) {
		int statusCode = HttpStatus.SC_BAD_REQUEST;
		if (Objects.nonNull(upsertResult)) {
			statusCode = upsertResult.getHttpCode();
			String upsertResultNotification = String.format(UPSERT_RESULT_NOTIFICATION, objectName, processName,
					upsertResult.getKey(), statusCode, upsertResult.getStatus(), upsertResult.getMessage());
			if (!isUpsertedSucceed(statusCode)) {
				Ivy.log().error(upsertResultNotification);
			} else {
				Ivy.log().warn(upsertResultNotification);
			}
		}
		return convertIPeopleUpsertResultToRequestResponse(upsertResult);
	}

	public static String getFoObjectExternalCode(FoObject object) {
		return Optional.ofNullable(object).map(FoObject::getExternalCode).orElse(null);
	}

	public static String getFoObjectName(FoObject object) {
		return Optional.ofNullable(object).map(FoObject::getName).orElse(null);
	}

	public static String convertLocalDateToIPepleEpochPattern(LocalDate date) {
		if (Objects.nonNull(date)) {
			long endDateInMiliEpoch = DateUtils.getEpochTimeFromLocalDate(date);
			return EPOCH_DATE_FORMAT.formatted(endDateInMiliEpoch);
		}
		return null;
	}

	public static IPeopleUser getUserByPositionCode(String positionCode) {
		IPeopleUser user = null;
		if (StringUtils.isNotBlank(positionCode)) {
			IPeopleJobService jobService = new IPeopleJobService();
			String managerId = jobService.findUserIdByPositionCode(positionCode);
			if (StringUtils.isNotBlank(managerId)) {
				IPeopleUserService userService = new IPeopleUserService();
				List<IPeopleUser> userList = userService.findById(managerId);
				user = !CollectionUtils.isNotEmpty(userList) ? null : userList.get(0);
			}
		}
		return user;
	}

	public static String getFullNameWithEmailFromPositionCode(String positionCode) {
		IPeopleUser user = getUserByPositionCode(positionCode);
		if (Objects.nonNull(user)) {
			return IPeopleUserUtils.getFullNameWithEmailAndId(user);
		}
		return null;
	}

	public static String findValidIPeopleUserIdOfIvyUser(IUser ivyUser) {
		IPeopleUser user = new IPeopleUserService().findByEmail(getEmailAddressOfIvyUser(ivyUser));
		// RHT-3868 IPeopleUser::getUserId was IPeopleUser::getIdentityNumber and is
		// only used in WorkerJobDataRequestService
		return Optional.ofNullable(user).map(IPeopleUser::getUserId).orElse(IPeopleConstants.DEFAULT_INVALID_USER_ID);
	}

	public static String convertBooleanToYesNoPicklistOption(Boolean value) {
		if (Objects.isNull(value)) {
			return null;
		}
		return value ? IPeopleConstants.OPTION_YES__EXT_CODE_FROM_YES_NO_PICKLIST
				: IPeopleConstants.OPTION_NO__EXT_CODE_FROM_YES_NO_PICKLIST;
	}

	public static String convertBooleanToYesNoLowerCasePicklistOption(Boolean value) {
		if (Objects.isNull(value)) {
			return null;
		}
		return value ? IPeopleConstants.OPTION_YES__EXT_CODE_FROM_YES_NO_LOWER_CASE_PICKLIST
				: IPeopleConstants.OPTION_NO__EXT_CODE_FROM_YES_NO_LOWER_CASE_PICKLIST;
	}

	public static String getPicklistOptionIdByExternalCodeAndService(FoObject option, IPeoplePicklistId picklistId) {
		String optionCode = IPeopleUtils.getFoObjectExternalCode(option);
		if (StringUtils.isAllBlank(optionCode)) {
			return null;
		}
		return getPicklistOptionIdByExternalCodeAndService(optionCode, picklistId);
	}

	public static String getPicklistOptionIdByExternalCodeAndService(String option, IPeoplePicklistId picklistId) {
		if (StringUtils.isAllBlank(option)) {
			return null;
		}
		return new IPeoplePickListValueV2Service(picklistId).getOptionIdByExternalCode(option);
	}

	public static String getPicklistOptionIdByExternalCodeAndParentFieldAndService(FoObject option,
			IPeoplePicklistId picklistId, String parrentField) {
		String optionCode = IPeopleUtils.getFoObjectExternalCode(option);
		if (StringUtils.isAllBlank(optionCode) || StringUtils.isAllBlank(parrentField)) {
			return null;
		}
		return new IPeoplePickListValueV2Service(picklistId).getOptionIdByExternalCodeAndParrentField(optionCode,
				parrentField);
	}

	public static String extractErrorReasonFromMessage(String message) {
		if (StringUtils.isNotBlank(message)) {
			return message.split(IPeopleConstants.RECORD_DETAIL_START_KEYWORD)[0];
		}
		return message;
	}

	public static Boolean isFoObjectContainExternalCode(FoObject object) {
		return StringUtils.isNotBlank(IPeopleUtils.getFoObjectExternalCode(object));
	}

	public static RequestResponse convertIPeopleUpsertResultToRequestResponse(SFODataUpsertResult result) {
		Objects.requireNonNull(result);
		RequestResponse response = new RequestResponse();
		String status = isUpsertedSucceed(result.getHttpCode()) ? IPeopleConstants.REQUEST_SUCCEED_STATUS
				: IPeopleConstants.REQUEST_FAILED_STATUS;
		response.setAuditingDisabled(true);
		response.setKey(result.getKey());
		response.setHttpCode(result.getHttpCode());
		response.setStatusCode(result.getStatus());
		response.setStatus(status);
		response.setMessage(extractErrorReasonFromMessage(result.getMessage()));
		return response;
	}

	public static RequestResponse sendToIPeople(Object iPeopleEntity, String processName) {
		IPeopleUpsertService upsertService = new IPeopleUpsertService();
		SFODataUpsertResult result = upsertService.upsertEntity(iPeopleEntity);
		return handleUpsertResponseResultMessage(result, processName, iPeopleEntity.getClass().getSimpleName());
	}
}
