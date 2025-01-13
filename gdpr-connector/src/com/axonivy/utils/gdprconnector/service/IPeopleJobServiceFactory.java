package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NAVIGATION_SEPARATOR;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.BUSINESSUNIT;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.COMPANY;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CONTRACT_TYPE;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.COST_CENTER;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_30;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_32;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_37_NAV;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_38;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_42;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_5;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_6;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.CUSTOM_STRING_93;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.DEPARTMENT;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.DIVISION;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.EMPLOYEE_CLASS;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.EMPLOYEE_TYPE;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.EMPLOYMENT_TYPE_NAV;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.EMPL_STATUS;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.LOCATION;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.MANAGER;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.MANAGER_USER;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.NOTICE_PERIOD;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.PERSON;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.PERSONAL_INFO;
import static com.axonivy.utils.gdprconnector.enums.IPeopleNavFields.USER;

import java.util.List;

import com.axonivy.connector.successfactors.connector.rest.SFODataEmpJob;
import com.axonivy.utils.gdprconnector.bo.IPeopleCriteria;
import com.axonivy.utils.gdprconnector.bo.IPeopleJob;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;

public class IPeopleJobServiceFactory extends IPeopleServiceFactory<IPeopleJob, SFODataEmpJob> {
	@Override
	public IPeopleService<IPeopleJob, SFODataEmpJob> getService() {
		return new IPeopleJobService();
	}

	public static final List<String> getDefaultOrderby() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.SEQ_NUMBER.getName() + " desc");
		return result;
	}

	public static final List<String> getDefaultSelection() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.JOB_TITLE.getName());
		result.add(IPeopleField.STANDARD_HOURS.getName());
		result.add(IPeopleField.WORKINGDAYS_PER_WEEK.getName());
		result.add(IPeopleField.CUSTOM_STRING_94.getName());
		result.add(IPeopleField.CONTRACT_END_DATE.getName());
		result.add(IPeopleField.CUSTOM_DATE_11.getName());
		
		return result;
	}

	public static final List<String> getNecessaryFieldsForOrganisationalChange() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(BUSINESSUNIT.getName());
		result.add(DIVISION.getName());
		result.add(DEPARTMENT.getName());
		result.add(MANAGER.getName() + NAVIGATION_SEPARATOR + PERSON.getName() + NAVIGATION_SEPARATOR
				+ PERSONAL_INFO.getName());
		result.add(MANAGER.getName() + NAVIGATION_SEPARATOR + USER.getName());
		result.add(EMPL_STATUS.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(COST_CENTER.getName());
		result.add(MANAGER_USER.getName() + NAVIGATION_SEPARATOR + IPeopleField.HR.getName());
		result.add(MANAGER_USER.getName() + NAVIGATION_SEPARATOR + IPeopleField.MATRIX_MANAGER.getName());
		result.add(LOCATION.getName());
		result.add(CUSTOM_STRING_93.getName());
		result.add(COMPANY.getName() + NAVIGATION_SEPARATOR + IPeopleField.LEGAL_ENTITY.getName());
		return result;
	}

	public static final List<String> getNecessaryFieldsForWorkerJob() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(BUSINESSUNIT.getName());
		result.add(DIVISION.getName());
		result.add(DEPARTMENT.getName());
		result.add(MANAGER.getName());
		result.add(EMPL_STATUS.getName());
		result.add(EMPL_STATUS.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(COST_CENTER.getName());
		result.add(MANAGER.getName() + NAVIGATION_SEPARATOR + PERSON.getName() + NAVIGATION_SEPARATOR
				+ PERSONAL_INFO.getName());
		result.add(MANAGER_USER.getName() + NAVIGATION_SEPARATOR + IPeopleField.HR.getName());
		result.add(MANAGER_USER.getName() + NAVIGATION_SEPARATOR + IPeopleField.MATRIX_MANAGER.getName());
		result.add(LOCATION.getName());
		result.add(CUSTOM_STRING_93.getName());
		result.add(CUSTOM_STRING_5.getName());
		result.add(CUSTOM_STRING_6.getName());
		result.add(CUSTOM_STRING_42.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(COMPANY.getName() + NAVIGATION_SEPARATOR + IPeopleField.LEGAL_ENTITY.getName());
		result.add(MANAGER.getName() + NAVIGATION_SEPARATOR + USER.getName());
		result.add(CONTRACT_TYPE.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(NOTICE_PERIOD.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(EMPLOYEE_TYPE.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(EMPLOYEE_CLASS.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(CUSTOM_STRING_30.getName());
		result.add(CUSTOM_STRING_32.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(CUSTOM_STRING_37_NAV.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(CUSTOM_STRING_38.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		result.add(EMPLOYMENT_TYPE_NAV.getName() + NAVIGATION_SEPARATOR + IPeopleField.PICKLIST_LABEL.getName());
		return result;
	}

	public static final List<String> getUserIdField() {
		List<String> select = new ch.ivyteam.ivy.scripting.objects.List<>();
		select.add(IPeopleField.USERID.getName());
		return select;
	}

	public static final List<String> getLegalEntityAndManagerEmployeeField() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(COMPANY.getName() + NAVIGATION_SEPARATOR + IPeopleField.LEGAL_ENTITY.getName());
		result.add(MANAGER.getName() + NAVIGATION_SEPARATOR + USER.getName());
		return result;
	}

	public static final IPeopleCriteria getLegalAndManagerEmployeeCriteria(String id) {
		IPeopleCriteria criteria = new IPeopleCriteria(id);
		criteria.setExpands(getLegalEntityAndManagerEmployeeField().stream().toArray(String[]::new));
		criteria.setSelections(getLegalEntityAndManagerEmployeeField().stream().toArray(String[]::new));
		return criteria;
	}

	public static final IPeopleCriteria getWorkerJobChangeDataCriteria(String id) {
		IPeopleCriteria criteria = new IPeopleCriteria(id);
		criteria.setExpands(getNecessaryFieldsForWorkerJob().stream().toArray(String[]::new));

		List<String> selections = ch.ivyteam.ivy.scripting.objects.List.create(String.class,
				getNecessaryFieldsForWorkerJob());
		selections.addAll(getDefaultSelection());
		selections.add(IPeopleField.POSITION.getName());
		selections.add(IPeopleField.CUSTOM_DATE_2.getName());
		selections.add(IPeopleField.FTE.getName());
		selections.add(IPeopleField.PROBATION_END_DATE.getName());
		selections.add(IPeopleField.IS_COMPETITION_CLAUSE_ACTIVE.getName());
		selections.add(IPeopleField.CUSTOM_DATE_11.getName());
		criteria.setSelections(selections.stream().toArray(String[]::new));
		return criteria;
	}

	public static final IPeopleCriteria getOrganisationalChangeRequestCriteria(String id) {
		IPeopleCriteria criteria = new IPeopleCriteria(id);
		criteria.setExpands(getNecessaryFieldsForOrganisationalChange().stream().toArray(String[]::new));

		List<String> selections = ch.ivyteam.ivy.scripting.objects.List.create(String.class,
				getNecessaryFieldsForWorkerJob());
		selections.addAll(getNecessaryFieldsForOrganisationalChange());
		criteria.setSelections(selections.stream().toArray(String[]::new));
		return criteria;
	}
}
