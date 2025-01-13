package com.axonivy.utils.gdprconnector.enums;

public enum HRTicketType  implements HasCmsName {
	SQ_IPERFORM,
	SQ_ILEARN,
	SQ_IPEOPLE,
	SQ_ICERTIFY,
	SQ_IPEOPLE_2,
	SQ_TALENT_LINK,

	WI_ACCESS_TO_DASHBOARD_INSIGHTS,
	WI_UPDATE_TO_DASHBOARD_INSIGHTS,
	WI_DEVELOP_NEW_DASHBOARD_INSIGHTS,
	WI_AD_HOC_DATA_REPORT_REQUEST,

	LSR_REQUEST_FOR_LD_ADVICE_LEARNING_SUPPORT, LSR_SUPPORT_TO_SETUP_VIRTUAL_TRAINING_ROOMS_INVITES,
	LSR_LEARNING_REPORT_REQUEST,
	LSR_LD_GENERAL_QUERIES, LSR_LD_ACTIVITY_REQUEST,

	// Employee/Personal queries
	PQ_EMPLOYEE_BENEFITS,
	PQ_PAYROLL,
	PQ_GENERAL,
	PQ_EMPLOYEE_RELATIONS,

	// Manage Employee Data: Organisation Management
	MED_MAINTAIN_ORGANISATION_STRUCTURE,
	MED_MAINTAIN_POSITIONS,

	// Manage Employee Data: Worker Data Management
	MED_UPDATE_WORKER_PERSONAL_DATA, MED_UPDATE_WORKER_JOB_DATA,

	// Manage Employee Administration: Absence Management
	MEA_MANAGE_PERSONAL_LEAVE, MEA_MANAGE_SICKNESS_LEAVE, MEA_MANAGE_FAMILY_LEAVE, MEA_MANAGER_LEAVE_OTHER,
	MEA_PROCESS_RETURN_FROM_LEAVE_OF_ABSENCE,

	// Manage Employee Administration: Separation
	MEA_MANAGE_VOLUNTARY_SEPARATIONS,

	KB_ADD_NEW_CONTENT,
	KB_EDIT_DELETE;

	public String getNameWithCategory() {
		return getCms("category") + " > " + getCms("name");
	}

	public String getNameWithCategory(String separatorFormat) {
		return getCms("category") + separatorFormat + getCms("name");
	}
}
