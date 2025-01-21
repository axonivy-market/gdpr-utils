package com.axonivy.utils.gdpr.enums;

public enum CustomField {
	/**
	 * Embed GUI in current frame.
	 *
	 * Set this to <code>false</code> in cases of a GUI to avoid having a second
	 * IFrame.
	 */
	EMBED_IN_FRAME("embedInFrame"), ENTITY_ID("entityId"),
	CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE("CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE"),
	HIGHEST_STATE("highestState"), RESPONSE_BY_RAISER_TASK("responseByRaiserTask"),
	RESPONSE_BY_RAISER_CHANGED("responseByRaiserChanged"),
	PRE_HIRE_PERSONAL_EMAIL_ADDRESS("PRE_HIRE_PERSONAL_EMAIL_ADDRESS"), HIDE("HIDE"),
	COMPLETE_ONBOARDING_INFORMATION_REWORK_TASK("completeOnboardingInformationReworkTask"), LEGAL_ENTITY("LegalEntity"),
	RESET_ESCALATION_STAGE("RESET_ESCALATION_STAGE"), NEW_EXPIRY_DATE("NEW_EXPIRY_DATE"), REQUEST_OWNER("requestOwner"),
	PRE_HIRE_PERSONAL_EMAIL_ADDRESS_IN_ONBOARDING_PROCESS_SCOPE("preHirePersonalEmailInOnboardingProcessScope"),
	LEGAL_ENTITY_CODE("LegalEntityCode"), EMPLOYEE_NUMBER("EmployeeNumber"),
	PRE_HIRE_ONBOARDING_APPLICATION_ID("preHireOnboardingApplicationId"), TOTAL_RETRY_SEND_MAIL("totalRetrySendMail"),
	REPORTING_ORIGINAL_EXPIRY_DATE("reportingOriginalExpiryDate"), REPORTING_NEW_EXPIRY_DATE("reportingNewExpiryDate"),
	BUSINESS_EMAIL_ADDRESS("businessEmailAddress"), EMPLOYEE_SCREENING_APPLICATION_ID("employeeScreeningApplicationId"),
	PEOPLE_APPLICATION_ID("peopleApplicationId"), PROCESS_ID("ProcessId"), CURRENT_TASK("CurrentTask"),
	INTERNAL_OFFER_APPLICATION_ID("internalOfferApplicationId"), CREATED_USER_ID("createdUserID"),
	IPEOPLE_EMPLOYEE_ID("iPeopleEmployeeId"), WORK_COUNCIL_APPROVAL_TASK_ID("WorkCouncilApprovalTaskId"),
	ENTITY_ID_WITH_STATIC_TALENT_LINK_DATA("entityIdWithStaticTalentLinkData"), CASE_ID("CaseId"),
	PROCESSING_TIMES("ProcessingTimes"), IS_DONE_TASK_WITH_FIXED_PROCESSING_TIMES("IsDoneTaskWithFixedProcessingTimes"),
	DELETED_FINANCIAL_YEARS("DeletedFinancialYears"), DATA_DELETED_BY_FY("DataDeletedByFinancialYear"),
	DATA_DELETION_MESSAGE_ID("DataDeletionMessageId"), SIGNAL_DATA("SignalData"),
	HR_TICKET_REQUEST_SERVICE_NOW("HrTicketRequestServiceNow");
	

	private String fieldName;

	private CustomField(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Get the field name.
	 *
	 * This is the name to use as customField name.
	 *
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}
}
