package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AT;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EMPLOYMENT_ACTIVE_STATUS;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.LIKE;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NAVIGATION_SEPARATOR;

import java.util.List;

import com.axonivy.connector.successfactors.connector.rest.SFODataUser;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.IPeopleNavFields;

public class IPeopleUserServiceFactory extends IPeopleServiceFactory<IPeopleUser, SFODataUser> {
	@Override
	public IPeopleService<IPeopleUser, SFODataUser> getService() {
		return new IPeopleUserService();
	}

	public static List<String> getDefaultSelection() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.USERID.getName());
		result.add(IPeopleField.FIRST_NAME.getName());
		result.add(IPeopleField.LAST_NAME.getName());
		result.add(IPeopleField.EMAIL.getName());
		result.add(IPeopleField.USERNAME.getName());
		result.add(IPeopleField.STATUS.getName());
		result.add(IPeopleField.DISPLAY_NAME.getName());
		result.add(IPeopleField.DEFAULT_FULL_NAME.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.JOB_INFO_NAV.getName()
				+ NAVIGATION_SEPARATOR + IPeopleField.COMPANY.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleField.END_DATE.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.PERSON.getName()
				+ NAVIGATION_SEPARATOR + IPeopleNavFields.NATIONAL_ID.getName() + NAVIGATION_SEPARATOR
				+ IPeopleField.NATIONAL_ID.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.PERSON.getName()
				+ NAVIGATION_SEPARATOR + IPeopleNavFields.NATIONAL_ID.getName() + NAVIGATION_SEPARATOR
				+ IPeopleField.IS_PRIMARY.getName());
		result.add(IPeopleField.SALUTATION.getName());
		return result;
	}

	public static List<String> getDefaultOrderby() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.LAST_NAME.getName());
		result.add(IPeopleField.FIRST_NAME.getName());
		return result;
	}

	public static List<String> getDefaultExpand() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleNavFields.EMP_INFO.getName());
		result.add(
				IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.JOB_INFO_NAV.getName());
		result.add(IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.PERSON.getName()
				+ NAVIGATION_SEPARATOR + IPeopleNavFields.NATIONAL_ID.getName());
		return result;
	}

	public static List<String> selectForLegalEntitySync() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.USERID.getName());
		result.add(IPeopleField.EMAIL.getName());
		result.add(IPeopleField.CUSTOM_05.getName());
		return result;
	}

	public static String createEmplActiveStatusFilter() {
		return String.format(EQUAL_FILTER_PATTERN, IPeopleField.STATUS.getName(), EMPLOYMENT_ACTIVE_STATUS);
	}

	public static String createEmplAvailableUsernameFilter() {
		return IPeopleField.USERNAME.getName() + String.format(LIKE, AT);
	}
}
