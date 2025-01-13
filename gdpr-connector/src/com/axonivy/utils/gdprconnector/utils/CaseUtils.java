package com.axonivy.utils.gdprconnector.utils;

import static com.axonivy.utils.gdprconnector.Constants.CASE_ID;
import static com.axonivy.utils.gdprconnector.Constants.ID;
import static com.axonivy.utils.gdprconnector.Constants.PREHIRE_PERSONAL_INFORMATION_ID;
import static com.axonivy.utils.gdprconnector.Constants.REQUEST_ID;
import static com.axonivy.utils.gdprconnector.enums.CustomField.EMBED_IN_FRAME;

import java.util.HashMap;
import java.util.Map;

public class CaseUtils {
	private CaseUtils() {
	}

	public static String getVoluntarySeparationCaseDetailsUrl(String voluntarySeparationId) {
		Map<String, String> params = initRequestParam();
		params.put("voluntarySeparationId", voluntarySeparationId);
		return ProcessStartUtils.buildUrl("Business Processes/ManageVoluntarySeparationDetails/businessDetails.ivp",
				params);
	}

	public static String getCaseDetailsUrl(String caseId) {
		Map<String, String> params = new HashMap<>();
		addCaseIdParam(caseId, params);
		return ProcessStartUtils.buildUrl("Start Processes/PortalStart/CaseDetailsPage.ivp", params);
	}

	public static String getAbsoluteCaseDetailsUrl(String caseId) {
		Map<String, String> params = new HashMap<>();
		addCaseIdParam(caseId, params);
		return ProcessStartUtils.buildAbsoluteUrl("Start Processes/PortalStart/CaseDetailsPage.ivp", params);
	}

	public static String getBusinessCaseDetailsUrlImagineChaneAward(String imagineChangeAwardId) {
		Map<String, String> params = initRequestParam();
		params.put("imagineChangeAwardId", imagineChangeAwardId);
		return ProcessStartUtils.buildUrl("Business Processes/ImagineChangeAwardDetails/businessDetails.ivp", params);
	}

	public static String getInitiatePeopleBusinessCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiatePeopleDetails/businessDetails.ivp", params);
	}

	public static String getEmployeeScreeningBusinessCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiateEmployeeScreeningDetails/businessDetails.ivp",
				params);
	}

	public static String getUpdateWorkerPersonalDataCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/UpdateWorkerPersonalDataDetails/businessDetails.ivp",
				params);
	}

	public static String getProvisioningCaseDetailsUrl(String id, String preHirePersonalInformationId) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		addPreHirePersonalInformationIdParam(preHirePersonalInformationId, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiateProvisioningDetails/businessDetails.ivp", params);
	}

	public static String getCreateHRTicketRequestCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/CreateHRTicketRequestDetails/businessDetails.ivp",
				params);
	}

	public static String getMaintainOrganisationStructureCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/MaintainOrganizationStructureDetails/businessDetails.ivp",
				params);
	}

	public static String getNewHireDataCheckCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/NewHireDataCheckDetails/businessDetails.ivp",
				params);
	}

	public static String getPrehireOnboardingBusinessCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiatePreHireOnboardingDetails/businessDetails.ivp",
				params);
	}

	public static String getAbsoluteURLUpdateWorkerJobData() {
		Map<String, String> params = initRequestParam();
		return ProcessStartUtils.buildAbsoluteUrl("Business Processes/UpdateWorkerJobData/start.ivp", params);

	}

	public static String getMaintainPositionsCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/MaintainPositionsDetails/businessDetails.ivp",
				params);
	}

	public static String getUpdateWorkerJobDataCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/UpdateWorkerJobDataDetails/businessDetails.ivp",
				params);
	}

	public static String getManageNewHireReschedulingAndNoShowsCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/ManageNewHireReschedulingAndNoShowsDetails/businessDetails.ivp",
				params);
	}

	public static String getInternalOfferCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addRequestIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InternalOffersDetails/businessDetails.ivp", params);
	}

	public static String getInternalOfferViewNotPermittedPageUrl(String message) {
		Map<String, String> params = initRequestParam();
		params.put("message", message);
		return ProcessStartUtils.buildUrl("Business Processes/InternalOffersDetails/showViewNotPermittedPage.ivp",
				params);
	}
	
	public static String getCreateKnowledgeBaseCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/CreateKnowledgeBaseProcessDetails/businessCaseDetails.ivp",
				params);
	}
	
	public static String getInitiateLogisticsCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiateLogisticsDetails/businessCaseDetails.ivp",
				params);
	}
	
	public static String getOnboardWorkerFirstDayOrientationCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/OnboardWorkerFirstDayOrientationDetails/businessCaseDetails.ivp",
				params);
	}
	
	public static String getInitiateSecondDayJoiningActionsCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils.buildUrl("Business Processes/InitiateSecondDayJoiningActionsDetails/businessCaseDetails.ivp",
				params);
	}
	
	public static String getTenureAnniversaryProgrammeCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils
				.buildUrl("Business Processes/TenureAnniversaryProgrammeDetails/businessCaseDetails.ivp", params);
	}

	public static String getDriverLicenseExpiryCaseDetailsUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils
				.buildUrl("Business Processes/DriverLicenseExpiryDataDetails/businessCaseDetails.ivp", params);
	}
	
	public static String getDataDeleteionCaseDetailUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		return ProcessStartUtils
				.buildUrl("Business Processes/DataDeletionDetails/businessCaseDetails.ivp", params);
	}

	private static Map<String, String> initRequestParam() {
		Map<String, String> params = new HashMap<>();
		params.put(EMBED_IN_FRAME.getFieldName(), "true");
		return params;
	}

	private static void addRequestIdParam(String id, Map<String, String> params) {
		params.put(REQUEST_ID, id);
	}

	private static void addCaseIdParam(String caseId, Map<String, String> params) {
		params.put(CASE_ID, caseId);
	}

	private static void addIdParam(String id, Map<String, String> params) {
		params.put(ID, id);
	}

	private static void addPreHirePersonalInformationIdParam(String id, Map<String, String> params) {
		params.put(PREHIRE_PERSONAL_INFORMATION_ID, id);
	}
}
