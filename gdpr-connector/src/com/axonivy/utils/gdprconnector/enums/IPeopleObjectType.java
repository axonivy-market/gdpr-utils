package com.axonivy.utils.gdprconnector.enums;

public enum IPeopleObjectType {
	LEGAL_ENTITY("connector/LegalEntity"),
	BUSINESS_UNIT("connector/BusinessUnit"),
	COST_CENTER("connector/CostCenter"),
	GLOBAL_COST_CENTRE("connector/PYCostCentre", IPeopleField.EXTERNAL_CODE, IPeopleField.EXTERNAL_NAME),
	DEPARTMENT("connector/Department"),
	DIVISION("connector/Division"),	LOCATION("connector/Location"),
	EMP_WORK_PERMIT("connector/EmpWorkPermit"),
	PERSON("connector/Person", IPeopleField.PERSON_ID_EXTERNAL, IPeopleField.FORMAL_NAME,
			new IPeopleNavFields[] { IPeopleNavFields.PERSONAL_INFO }),
	JOB("connector/Job", IPeopleField.USERID, IPeopleField.JOB_TITLE),
	PICKLIST("connector/Picklist"),
	PICKLIST_LABEL("connector/PicklistLabel"),
	TERRITORY("connector/Territory", IPeopleField.TERRITORY_CODE, IPeopleField.TERRITORY_NAME),
	COUNTRY("connector/Country", IPeopleField.CODE, IPeopleField.EXTERNAL_NAME_DEFAULT),
	CURRENCY("connector/Currency", IPeopleField.CODE, IPeopleField.EXTERNAL_NAME_DEFAULT),
	PAYMENT_METHOD("connector/PaymentMethod", IPeopleField.EXTERNAL_CODE, IPeopleField.EXTERNAL_NAME_DEFAULT),
	MDF_ENUM_VALUE("connector/MDFEnumValue"),
	SERVICE_LINE("connector/ServiceLine"),
	HYPERION("connector/CustomHyperion"),
	REPORTING_ENTITY("connector/Company", IPeopleField.EXTERNAL_CODE),
	PAY_GRADE("connector/PayGrade"),
	POSITION("connector/Position", IPeopleField.CODE, IPeopleField.POSITION_TITLE),
	TERMINATION("connector/Termination"), EMEAHR("connector/EMEAHR"), JOB_CODE("connector/JobCode"),
	USER("connector/User", IPeopleField.EMPINFO_PERSON_ID_EXTERNAL, IPeopleField.DISPLAY_NAME),
	PERSONNEL_AREA("connector/PersonnelArea"),
	PERSONNEL_SUB_AREA("connector/PersonnelSubArea"),
	SECTION_1("connector/Section1"),
	SECTION_2("connector/Section2"),
	SECTION_3("connector/Section3"),
	SECTION_4("connector/Section4"),
	PICKLIST_VALUE_V2("connector/PickListValueV2", IPeopleField.EXTERNAL_CODE),
	UPSERT_REQUEST("connector/Upsert"),
	PAYROLL_CONTROLLING_AREA("connector/PYControllingArea", IPeopleField.EXTERNAL_CODE, IPeopleField.EXTERNAL_NAME),
	JOB_RELATIONSHIPS("connector/JobRelationship"), PER_GLOBAL_INFO_ZAF("connector/PerGlobalInfoZAF"),
	NON_RICOH_LOCATION("connector/NonRicohLocation"),
	PAY_GROUP("connector/PayGroup");

	private String subprocessName;
	private IPeopleField idField = IPeopleField.EXTERNAL_CODE;
	private IPeopleField nameField = IPeopleField.NAME;
	private IPeopleNavFields[] nameNav = new IPeopleNavFields[] {};

	private IPeopleObjectType(String subprocessName) {
		this.subprocessName = subprocessName;
	}

	private IPeopleObjectType(String subprocessName, IPeopleField idField) {
		this.subprocessName = subprocessName;
		this.idField = idField;
	}

	private IPeopleObjectType(String subprocessName, IPeopleField idField, IPeopleField nameField) {
		this.subprocessName = subprocessName;
		this.idField = idField;
		this.nameField = nameField;
	}

	private IPeopleObjectType(String subprocessName, IPeopleField idField, IPeopleField nameField, IPeopleNavFields[] nameNav) {
		this.subprocessName = subprocessName;
		this.idField = idField;
		this.nameField = nameField;
		this.nameNav = nameNav;
	}

	public String getSubprocessName() {
		return subprocessName;
	}

	public IPeopleField getIdField() {
		return idField;
	}

	public IPeopleField getNameField() {
		return nameField;
	}

	public IPeopleNavFields[] getNameNav() {
		return nameNav;
	}
}
