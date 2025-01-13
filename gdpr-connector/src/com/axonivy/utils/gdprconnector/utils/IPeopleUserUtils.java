package com.axonivy.utils.gdprconnector.utils;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EMPLOYMENT_ACTIVE_STATUS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataEmpJob;
import com.axonivy.connector.successfactors.connector.rest.SFODataPerNationalId;
import com.axonivy.connector.successfactors.connector.rest.SFODataUser;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;

public class IPeopleUserUtils {
	public static final String NAME_WITH_EMAIL_WITH_ID_FORMAT = "%s (%s - %s)";
	public static final String NAME_WITH_EMAIL_FORMAT = "%s (%s)";

	public static IPeopleUser convertToUser(SFODataUser dataUser) {
		if (dataUser == null || dataUser.getEmpInfo() == null) {
			return null;
		}
		IPeopleUser user = new IPeopleUser();
		user.setFirstName(dataUser.getFirstName());
		user.setLastName(dataUser.getLastName());
		user.setEmail(dataUser.getEmail());
		user.setUserId(dataUser.getUserId());
		user.setStatus(dataUser.getStatus());
		user.setDisplayName(dataUser.getDisplayName());
		user.setDefaultFullName(dataUser.getDefaultFullName());
		user.setLegalEntityDisplayName(dataUser.getCustom05());
		user.setUsername(dataUser.getUsername());
		Optional.ofNullable(dataUser.getEmpInfo()).ifPresent(empInfo -> {
			user.setPersonIdExternal(empInfo.getPersonIdExternal());
			user.setEndDate(empInfo.getEndDate());
			CollectionUtils.emptyIfNull(empInfo.getJobInfoNav()).stream().findFirst().ifPresent(empJob -> {
				user.setLegalEntityId(empJob.getCompany());
			});
			Optional.ofNullable(empInfo.getPersonNav()).ifPresent(personNav -> {
				user.setDateOfBirth(personNav.getDateOfBirth());
			});
		});
		user.setSalutation(dataUser.getSalutation());
		user.setNationalId(getNationalId(dataUser));
		if (CollectionUtils.isNotEmpty(dataUser.getMatrixManager())) {
			dataUser.getMatrixManager().stream().filter(iUser -> EMPLOYMENT_ACTIVE_STATUS.equals(iUser.getStatus()))
					.findFirst().ifPresent(matrixUser -> {
						user.setMatrixManager(convertToUser(matrixUser));
					});
		}
		return user;
	}

	private static String getNationalId(SFODataUser dataUser) {
		String nationalId = StringUtils.EMPTY;
		if (dataUser.getEmpInfo() != null && dataUser.getEmpInfo().getPersonNav() != null) {
			for (SFODataPerNationalId perNationalId : CollectionUtils
					.emptyIfNull(dataUser.getEmpInfo().getPersonNav().getNationalIdNav())) {
				if (BooleanUtils.isTrue(perNationalId.isIsPrimary())) {
					nationalId = perNationalId.getNationalId();
					break;
				}
			}
		}

		return nationalId;
	}

	public static IPeopleUser convertToUser(SFODataEmpJob empJob) {
		if (empJob == null || empJob.getUserNav() == null) {
			return null;
		}
		IPeopleUser user = new IPeopleUser();
		Optional.ofNullable(empJob.getUserNav()).ifPresent(useOfEmpjob -> {
			user.setFirstName(useOfEmpjob.getFirstName());
			user.setLastName(useOfEmpjob.getLastName());
			user.setDisplayName(useOfEmpjob.getDefaultFullName());
			user.setDefaultFullName(useOfEmpjob.getDefaultFullName());
			user.setEmail(useOfEmpjob.getEmail());
			user.setUserId(useOfEmpjob.getUserId());
		});
		Optional.ofNullable(empJob.getEmploymentNav()).ifPresent(empJobEmploymentNav -> {
			user.setPersonIdExternal(empJobEmploymentNav.getPersonIdExternal());
		});
		return user;
	}

	public static String getFullNameWithEmailAndId(IPeopleUser iPeopleUser) {
		if (iPeopleUser == null) {
			return null;
		}
		Optional<String> displayName = Optional.ofNullable(iPeopleUser.getDisplayName());
		Optional<String> email = Optional.ofNullable(iPeopleUser.getEmail());
		Optional<String> userId = Optional.ofNullable(iPeopleUser.getIdentityNumber());
		if (displayName.isEmpty()) {
			return userId.orElse(EMPTY);
		}
		if (email.isPresent() && userId.isPresent()) {
			return String.format(NAME_WITH_EMAIL_WITH_ID_FORMAT, displayName.get(), email.get(), userId.get());
		}
		if (email.isPresent()) {
			return String.format(NAME_WITH_EMAIL_FORMAT, displayName.get(), email.get());
		} else if (userId.isPresent()) {
			return String.format(NAME_WITH_EMAIL_FORMAT, displayName.get(), userId.get());
		} else {
			return displayName.get();
		}
	}

	public static String getFullNameWithEmail(IPeopleUser iPeopleUser) {
		if (iPeopleUser == null) {
			return null;
		}
		Optional<String> displayName = Optional.ofNullable(iPeopleUser.getDisplayName());
		Optional<String> email = Optional.ofNullable(iPeopleUser.getEmail());
		Optional<String> userId = Optional.ofNullable(iPeopleUser.getIdentityNumber());
		if (displayName.isEmpty()) {
			return userId.orElse(EMPTY);
		}
		if (email.isPresent()) {
			return String.format(NAME_WITH_EMAIL_FORMAT, displayName.get(), email.get());
		}
		return displayName.get();
	}
}
