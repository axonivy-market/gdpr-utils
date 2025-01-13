package com.axonivy.utils.gdprconnector.enums;

public enum IPeopleNavFields {
	PERSONAL_INFO("personalInfoNav"), EMAIL("emailNav"), BUSINESSUNIT("businessUnitNav"), DIVISION("divisionNav"),
	DEPARTMENT("departmentNav"), MANAGER("managerEmploymentNav"), EMPL_STATUS("emplStatusNav"), COMPANY("companyNav"),
	CUST_COST_CENTER("cust_costcenterNav"), EXTERNAL_NAME("externalNameNav"), CUST_OPCO("cust_opcoNav"),
	CUST_HYPERION_DESC("cust_hyperiondescNav"),
	COST_CENTER("costCenterNav"), PERSON("personNav"), MANAGER_USER("managerUserNav"), LOCATION("locationNav"),
	CUSTOM_STRING_30("customString30Nav"), CUSTOM_STRING_32("customString32Nav"), CUSTOM_STRING_38("customString38Nav"),
	CUSTOM_STRING_93("customString93Nav"), PAY_GRADE("payGradeNav"), CUST_BUSINESS_UNIT("cust_businessUnit"),
	CUST_TO_LEGAL_ENTITY("cust_ToLegalEntity"), COST_CENTRE_CUST_TO_LEGAL_ENTITY("cust_toLegalEntity"),
	PAYROLL_COST_CENTER("cust_PayrollCostCenterNav"), USER("userNav"), JOB_INFO_NAV("jobInfoNav"),
	EMPLOYMENT("employmentNav"), CONTRACT_TYPE("contractTypeNav"), NOTICE_PERIOD("noticePeriodNav"),
	POSITION("positionNav"), EMPLOYEE_TYPE("employeeTypeNav"),
	EMPLOYEE_CLASS("employeeClassNav"), VARIABLE_PAY("cust_varpayNav"), SCHEME("cust_schemeNav"),
	FREQUENCY("cust_freqNav"), BONUS_TYPE("cust_typeNav"), NATIONAL_ID("nationalIdNav"),
	DOCUMENT_TYPE("documentTypeNav"), COUNTRY("countryNav"), EMP_INFO("empInfo"), JOB_CODE("jobCodeNav"),
	CUST_DIVISION("cust_Division"), HR("hr"), REL_USER("relUserNav"), LOCAL_NAV_ZAF("localNavZAF"),
	CUSTOM_STRING_37_NAV("customString37Nav"),
	CUSTOM_STRING_5("customString5Nav"), CUSTOM_STRING_6("customString6Nav"),
	CUSTOM_STRING_42("customString42Nav"),
	CUST_GO_SECTION_1("cust_GO_SectionNav"),
	CUST_GO_SECTION_2("cust_GO_Section_2Nav"),
	CUST_GO_SECTION_3("cust_GO_Section_3Nav"),
	CUST_GO_SECTION_4("cust_GO_Section_4Nav"),
	CURRENCY("currencyNav"), HOME_ADDRESS("homeAddressNavDEFLT"), CITY("cityNav"),
	EMPLOYMENT_TYPE_NAV("employmentTypeNav");

	private String name;

	private IPeopleNavFields(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
