package com.axonivy.utils.gdpr.enums;

public enum IvyVariable {
	IPERFORM_LINK("com.ricoh.hr.workforceadmin.voluntaryseparation.iPerformLink"),
	EXPENSES_LINK("com.ricoh.hr.workforceadmin.voluntaryseparation.expensesLink"),
	IPEOPLEONE_LINK("com.ricoh.hr.workforceadmin.voluntaryseparation.iPeopleOneLink"),
	DAYS_BEFORE_TERMINATION_DATE_TO_SEND_REMINDER_TASK(
			"com.ricoh.hr.workforceadmin.voluntaryseparation.daysBeforeTerminationDateToSendReminderTask"),
	DAYS_AFTER_TERMINATION_DATE_TO_DESTROY_TERMINATION_TASK(
			"com.ricoh.hr.workforceadmin.voluntaryseparation.daysAfterTerminationDateToDestroyTerminationTask"),
	ILEARN_LINK("com.ricoh.hr.onboarding.iLearnLink"), IPEOPLE_LINK("com.ricoh.hr.onboarding.iPeopleLink"),
	RICOH_EMEA_EMAIL("com.ricoh.hr.core.ricohEMEAEmail"), RICOH_EUROPE_PLC("com.ricoh.hr.generic.ricoheuropeplc"),
	SKIP_DAY_ONE_READINESS_LEGAL_ENTITY_CODES("com.ricoh.hr.onboarding.skipDayOneReadinessLegalEntityCodes"),
	SCREENING_PROCESS_LEGAL_ENTITY_CODES("com.ricoh.hr.onboarding.screeningProcessLegalEntityCodes"),
	PRE_HIRE_ONBOARDING_TALENTLINK_QUEUE_FILTER("com.ricoh.hr.onboarding.talentlinkQueueFilter"),
	PRE_HIRE_ONBOARDING_TALENTLINK_CONTRACT_TYPE_FILTER("com.ricoh.hr.onboarding.talentlinkContractTypeFilter"),
	PRE_HIRE_ONBOARDING_PROCESS_LEGAL_ENTITY_CODES("com.ricoh.hr.onboarding.preHireOnboardingProcessLegalEntityCodes"),
	STARTABLE_ONBOARDING_PROCESS_LEGAL_ENTITY_CODES(
			"com.ricoh.hr.onboarding.startableOnboardingProcessLegalEntityCodes"),
	NO_SUGGEST_NEW_START_DATE_LEGAL_ENTITY_CODES("com.ricoh.hr.onboarding.noSuggestNewStartDateOptionLegalEntityCodes"),
	ENABLE_FETCH_BUSINESS_EMAIL_FROM_SERVICE_NOW("com.ricoh.hr.onboarding.enableFetchBusinessEmailFromServiceNow"),
	TALENT_LINK_LOV_ID("com.ricoh.hr.onboarding.talentLinkLovId"),
	HR_SERVICE_CENTER_PHONE_NUMBER("com.ricoh.hr.onboarding.hRServiceCenterPhoneNumber"),
	PSO_EMAIL_ADDRESS("com.ricoh.hr.onboarding.PSOEmailAddress"),
	DAYS_BEFORE_START_DATE_FOR_INITIATE_PEOPLE("com.ricoh.hr.onboarding.daysBeforeStartDateForInitiatePeople"),
	DAYS_BEFORE_START_DATE_FOR_SECOND_DAY("com.ricoh.hr.onboarding.daysBeforeStartDateForSecondDay"),
	TASK_EXPIRY_OFFSET_DAYS_PREHIRE("com.ricoh.hr.onboarding.taskExpiry.offsetDays.preHire"),
	TASK_EXPIRY_OFFSET_DAYS_ONBOARDING_CONSULTANT("com.ricoh.hr.onboarding.taskExpiry.offsetDays.onboardingConsultant"),
	TASK_EXPIRY_OFFSET_DAYS_MANAGER("com.ricoh.hr.onboarding.taskExpiry.offsetDays.manager"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_SCREENING_COMPLETE_REFERENCE_INFORMATION(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.screening.completeReferenceInformation"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_SCREENING_COMPLETE_EMPLOYEE_SCREENING(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.screening.completeEmployeeScreening"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PREHIRE_INVITE_FOR_PREHIRE_ONBOARDING(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.preHire.inviteForPreHireOnboarding"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PREHIRE_COMPLETE_ONBOARDING_INFORMATION(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.preHire.completeOnboardingInformation"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PREHIRE_CHECK_DAY_ONE_READINESS(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.preHire.checkDayOneReadiness"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PREHIRE_REPLY_TO_DAY_ONE_READINESS(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.preHire.replyToDayOneReadiness"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PROVISION_UPDATE_INCOMPLETE_FIELD(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.provision.updateIncompleteField"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PROVISION_ENHANCE_EMPLOYEE_DATA(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.provision.enhanceEmployeeData"),
	TASK_EXPIRY_PREHIRE_ONBOARDING_PEOPLE_MANAGER_CHECKLIST(
			"com.ricoh.hr.onboarding.taskExpiry.preHireOnboarding.people.managerChecklist"),
	TASK_EXPIRY_RESPONSE_BY_RAISER("com.ricoh.hr.ticketing.taskExpiry.responseByRaiser"),
	TASK_EXPIRY_SOLVED_BY_AGENT("com.ricoh.hr.ticketing.taskExpiry.solvedByAgent"),
	COMPLETE_ONBOARDING_STUDENT_LOAN_STARTER_CHECKLIST_LINK("com.ricoh.hr.onboarding.studentLoanStarterChecklistLink"),
	COMPLETE_ONBOARDING_P45_STARTER_CHECKLIST_LINK("com.ricoh.hr.onboarding.p45StarterChecklistLink"),
	VOLUNTARY_SEPARATION_EVENT_REASON_LIST(
			"com.ricoh.hr.workforceadmin.voluntaryseparation.voluntarySeparationEventReason"),
	VOLUNTARY_SEPARATION_LEGAL_ENTITY_LIST(
			"com.ricoh.hr.workforceadmin.voluntaryseparation.voluntarySeparationProcessLegalEntityCodes"),
	NO_EMAIL_TO_FACILITY_AND_FINANCE_FOR_VOLUNTARY_SEPARATION_LEGAL_ENTITY_CODES(
			"com.ricoh.hr.workforceadmin.voluntaryseparation.noEmailToFacilityAndFinanceForVoluntarySeparationLegalEntityCodes"),
	ENABLE_UPDATING_POSITION_TO_IPEOPLE(
			"com.ricoh.hr.workforceadmin.positionmaintenace.enableAutoUpdatePositionInIpeople"),
	ENABLE_AUTO_GENERATE_POSITION_CODE("com.ricoh.hr.workforceadmin.positionmaintenace.autoGeneratePositionCode"),
	NOT_SET_DEFAULT_PAY_GRADE_FOR_LEGAL_ENTITIES(
			"com.ricoh.hr.workforceadmin.positionmaintenace.notSetDefaultPayGradeForLegalEntities"),
	DAYS_FOR_SENDING_EMAIL_TO_INFORM_DRIVER_LICENSE_EXPIRY_DATE(
			"com.ricoh.hr.workforceadmin.regularcheckfordriverlicenseexpiries.daysForSendingEmailToInformDriverLicenseExpiryDate"),
	ENABLE_CREATING_EMPLOYEE_PROFILE_ON_IPEOPLE("com.ricoh.hr.onboarding.enableCreatingEmployeeProfileOnIPeople"),
	EDITABLE_LEGAL_ENTITY_FOR_PAYGRADE("com.ricoh.hr.workforceadmin.positionmaintenace.editableLegalEntityForPayGrade"),
	ALLOW_TYPES_FOR_SIGN_ATTACHMENT("com.ricoh.hr.workforceadmin.workerjobdata.allowTypesForSignAttachment"),
	ROLES_DELEGATION_TASK("com.ricoh.hr.core.rolesDelegationTask"),
	UPSERTABLE_EMPLOYEE_PROFILE_LEGAL_ENTITY("com.ricoh.hr.onboarding.upsertableEmployeeProfileLegalEntity"),
	NO_EMAIL_TO_LOCAL_FACILITY_MANAGEMENT_FOR_LEGAL_ENTITY_CODES(
			"com.ricoh.hr.onboarding.logistics.noEmailToLocalFacilityManagementForLegalEntityCodes"),
	KB_EDITOR_TEMPLATES("com.ricoh.hr.knowledgebase.editorTemplates"),
	IMAGINE_CHANGE_AWARD_PROCESS_LEGAL_ENTITY_CODES("com.ricoh.hr.workforceadmin.imagineChangeAwardProcessLegalEntityCodes"),
	ISO_COUNTRIES("com.ricoh.hr.onboarding.ISOCountry"),
	SEND_PHYSICAL_FINAL_DOCUMENTATION_TO_EMPLOYEE_FOR_LEGAL_ENTITIES("com.ricoh.hr.workforceadmin.workerjobdata.sendPhysicalFinalDocumentationToEmployeeForLegalEntities"),
	SEND_EMAIL_WITH_FINAL_DOCUMENTATION_TO_EMPLOYEE_FOR_LEGAL_ENTITIES("com.ricoh.hr.workforceadmin.workerjobdata.sendEmailWithFinalDocumentationToEmployeeForLegalEntities"),
	DISABLE_ESIGN_FOR_LEGAL_ENTITIES("com.ricoh.hr.workforceadmin.workerjobdata.disableESignForLegalEntities"),
	USE_TALENT_LINK_STATIC_DATA("restrictedEntity.developerOptions.onboarding.useTalentLinkStaticData"),
	RESTRICTED_TALENT_LINK_STATIC_DATA("restrictedEntity.developerOptions.onboarding.talentLinkStaticData"),
	RESTRICTED_LEGAL_ENTITY_ID("restrictedEntity.developerOptions.onboarding.legalEntityId"),
	EXPIRY_DAYS_FOR_WORK_COUNCIL_REVIEW_APPROVAL_TASK(
			"com.ricoh.hr.workforceadmin.workerjobdata.expiryDaysForWorkCouncilReviewAndApprovalTask"),
	DATA_DELETION_INITIAL_FINANCIAL_YEAR("com.ricoh.hr.core.datadeletion.initialFinancialYear"),
	DATA_DELETION_LEGAL_ENTITIES_IN_SCOPE("com.ricoh.hr.core.datadeletion.legalEntitiesInScope"),
	DATA_DELETION_START_DATE_FINANCIAL_YEAR("com.ricoh.hr.core.datadeletion.startDateFinancialYear"),
	DATA_DELETION_MIN_RANGE_AFTER_END_OF_FINANCIAL_YEAR(
			"com.ricoh.hr.core.datadeletion.minRangeAfterEndOfFinancialYear"),
	DATA_DELETION_MAX_TOTAL_FINANCIAL_YEAR_CAN_BE_SELECTED(
			"com.ricoh.hr.core.datadeletion.maxTotalFinancialYearCanBeSelected");
	private String variableName;

	private IvyVariable(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableName() {
		return variableName;
	}
}
