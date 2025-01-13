package com.axonivy.utils.gdprconnector.enums;

public enum IPeoplePicklistId {
	ADDRESS_CATEGORY_RZA("ADDRESS_CATEGORY_ZAF"),
	ADDRESS_TYPE("addressType"),
	BONUS_FREQUENCY("cust_Frequency"),
	BONUS_SCHEME("cust_Bonus_Scheme"),
	CUST_BONUS_TYPE("cust_Type"),
	CONTRACT_TYPE("contractType"),
	COUNTRY_LIST("ISOCountryList"),
	COUNTY_LIST("COUNTY_GBR"),
	EMPLOYEE_CLASS("EMPLOYEECLASS"),
	EMPLOYEE_STATUS("employee-status"),
	EMPLOYEE_TYPE("employee-type"),
	EMPLOYMENT_TYPE("employmentType"),
	JOB_LEVEL("EMEAjobLevel"),
	HYBRID("HYBRID"),
	LANGUAGE("language"),
	MARITAL_STATUS("ecMaritalStatus"),
	NAME_PREFIX("nameprefix"),
	NOTICE_PERIOD("NOTICEPERI"),
	PAY_FREQUENCY("PayFrequency"),
	RBE_CITY("cust_BE_Postal_Country"),
	RBE_CONTRACT_CHAIN("cust_BE_ContractChain"),
	RBE_CAR_CATEGORY("cust_BE_CarCategory"),
	HIGHEST_LEVEL_OF_STUDY("cust_BNLX_LevelOfStudy"),
	RBE_HR_GRADE("cust_BE_HRGrade"),
	RBE_PAYROLL_GROUP("cust_BE_PayrollGroup"),
	RBE_POSTAL_CODE("cust_BE_PostalCode"),
	RELATIONSHIP("relation"),
	RFR_EXPENSE_PROFILE("cust_FR_expense_profile"),
	RFR_LUNCH_TYPE("cust_FR_lunchtype"),
	RFR_VARIABLE_COMPENSATION("cust_FR_variable_compensation"),
	RFR_WORK_CONTRACT("cust_WorkContract"),
	RES_CHALLENGE_GROUP("challengeGroup_ES"),
	RES_COLLECT_AGREE("cust_ES_CollectAgree"),
	RES_DEGREE_OF_CHALLENGE("DEGREEOFCHALLENGE_ESP"),
	RFR_DEPARTMENT("DEPARTMENT_FRA"),
	RES_LABOUR_RELATIONSHIP("cust_ES_LabourRel"),
	RES_ORG_ROLE("cust_ES_OrgRole"),
	RES_PAY_ADMIN("cust_ES_PayAdmin"),
	RES_PERSON_TYPE("cust_ES_PersonType"),
	RES_POSITION_FAMILY("CUST_POSFAMILY_ESP"),
	RES_PROVINCE("PROVINCE_ESP"),
	RES_STREET_TYPE("STRTYPE_ESP"),
	TYPE_OF_CHALLENGE("typeOfChallenge"),
	RES_WORK_COUNCIL("cust_ES_WorkCouncil"),
	RLU_ACADEMIC_TITLE("ACADEMIC_TITLE"),
	RLU_CONTRACT_CHAIN("cust_LU_ContractChain"),
	RLU_CAR_CATEGORY("cust_LU_CarCategory"),
	RLU_EMPLOYEE_REPRESENTATIVE("cust_LU_EERepresentative"),
	RLU_HR_GRADE("cust_LU_HRGrade"),
	RNL_CONTRACT_CHAIN("cust_NL_empContractWWZ"),
	RPT_COLLECT_AGREE("cust_PT_CollectAgree"),
	RPT_TYPE_OF_CHALLENGE("typeOfChallenge_PT"),
	SALUTATTON("salutation"),
	VARIABLE_PAY_TYPE("cust_variable_pay_type"),
	YES_NO("yesNo"),
	YES_NO_LOWERCASE("yesno"),
	RELIGION_CHE("RELIGION_CHE"),
	CANTON_CHE("CANTON_CHE"),
	RCH_EDUCATION("cust_CH_education"),
	EMPLOYER_NOTICE_PERIOD("employerNoticePeriod"),
	DEU_COLLECTIVE_BARGAINING_AGREEMENT("CollectiveBargaining"),
	I_PERFORM_SURVEY("iPerformSurvey"),
	EMPLOYEE_SURVEY("Employee_Survey"),
	COMPLIANCE("Compliance"),
	OSCAR_JOB_ROLE("OscarJobRole"),
	ITA_CITY("ITA_City_Picklist"),
	PROVINCE_ITA("PROVINCE_ITA"),
	PROVINCE_TUR("PROVINCE_TUR"),
	RAT_COLLECTIVE_BARGAINING_AGREEMENT("collectiveAggrement_AUT"),
	RCH_INTERNAL_HIERARCHY("cust_CH_internalHierarchy"),
	RCH_EXPENSES_PROFILE("cust_CH_expenseProfile"),
	RFR_EXPENSES_PROFILE("cust_FR_expense_profile"),
	RCH_TRANSPORT_ALLOWANCE("cust_CH_transportAllowance"),
	RCH_TIME_RECORDING("cust_CH_timeRecording"),
	RCH_FABI("cust_CH_FABI"),
	RTR_DEGREE_OF_CHALLENGE("degreeOfChallenge_TUR"),
	BLOODGROUP("BLOODGROUP"),
	RTR_EDUCATIONAL_STATUS("Education_Status_TUR"),
	RTR_LEVEL_OF_FOREIGN_LANGUAGE("Level_Of_Foreign_Language"),
	RIC_HUNG("cust_HU_Education"), 
	RIC_HUNG_QUALIFICATION("cust_HU_Subject"), 
	RIC_HUNG_STREET_TYPE("cust_HU_StreetType"),
	REGION_CZE("region_cze"),
	DEFAULT("");

	private String picklistName;

	private IPeoplePicklistId(String picklistName) {
		this.picklistName = picklistName;
	}

	public String getPicklistName() {
		return picklistName;
	}

	public static IPeoplePicklistId fromValue(String value) {
		for (IPeoplePicklistId picklistId : IPeoplePicklistId.values()) {
			if (picklistId.getPicklistName().equals(value)) {
				return picklistId;
			}
		}
		return DEFAULT;
	}
}
