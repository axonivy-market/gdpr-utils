package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AND;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AT;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.DATE_GREATER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.DATE_TIME_FORMAT;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EMPLOYMENT_ACTIVE_STATUS;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.INSIDE_PARENTHESIS_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.LIKE;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.LOWER_CASE_FILTER;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NAVIGATION_SEPARATOR;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.OR;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.SUBSTRING_FILTER_PATTERN;
import static java.lang.String.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.AllOfSFODataUserEmpInfo;
import com.axonivy.connector.successfactors.connector.rest.SFODataUser;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.IPeopleNavFields;
import com.axonivy.utils.gdprconnector.enums.IPeopleObjectType;
import com.axonivy.utils.gdprconnector.managedbean.IPeopleCacheBean;
import com.axonivy.utils.gdprconnector.utils.IPeopleUserUtils;

public class IPeopleUserService extends IPeopleAbstractUserService<SFODataUser> {
	@Override
	protected IPeopleObjectType getType() {
		return IPeopleObjectType.USER;
	}

	@Override
	protected String getReturnParam() {
		return "users";
	}

	@Override
	protected String getStartName() {
		return "getUsers";
	}

	@Override
	protected IPeopleUser mapResult(SFODataUser dataUser) {
		return IPeopleUserUtils.convertToUser(dataUser);
	}

	@Override
	protected List<String> getDefaultSelection() {
		return IPeopleUserServiceFactory.getDefaultSelection();
	}

	@Override
	protected List<String> getDefaultOrderby() {
		return IPeopleUserServiceFactory.getDefaultOrderby();
	}

	@Override
	protected List<String> getDefaultExpand() {
		return IPeopleUserServiceFactory.getDefaultExpand();
	}

	@Override
	public List<IPeopleUser> findByName(String name, Integer top) {
		var filterByName = "";
		if (StringUtils.contains(name, StringUtils.SPACE)) {
			// Search in case of firstName <SPACE> lastName (RHT-2241)
			var firstName = StringUtils.left(name, StringUtils.indexOf(name, StringUtils.SPACE));
			var lastName = StringUtils.substring(name, StringUtils.indexOf(name, StringUtils.SPACE) + 1);
			filterByName = format(INSIDE_PARENTHESIS_PATTERN, getFilterByName(firstName)) + AND
					+ format(INSIDE_PARENTHESIS_PATTERN, getFilterByName(lastName));
		} else {
			// Search in case of name without a <SPACE>
			filterByName = getFilterByName(name);
		}

		return findByFilter(format(INSIDE_PARENTHESIS_PATTERN, filterByName), top);
	}

	private String getFilterByName(String name) {
		return format(LOWER_CASE_FILTER, buildName(getType())) + format(LIKE, StringUtils.lowerCase(name)) + OR
				+ format(EQUAL_FILTER_PATTERN, format(SUBSTRING_FILTER_PATTERN, name, IPeopleField.USERID.getName()),
						"true");
	}

	@Override
	protected Predicate<? super SFODataUser> filterByPersonIdExternal(String customId) {
		return data -> StringUtils.isNoneBlank(data.getUserId()) && StringUtils.isNoneBlank(data.getEmail())
				&& StringUtils.equals(Optional.ofNullable(data.getEmpInfo())
						.map(AllOfSFODataUserEmpInfo::getPersonIdExternal).orElse(null), customId);
	}

	@Override
	protected String createPersonIdFilter(String personIdExternal) {
		return format(EQUAL_FILTER_PATTERN,
				IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleField.PERSON_ID_EXTERNAL.getName(),
				personIdExternal);
	}

	@Override
	protected String createAdditionalFilter() {
		return createEmptyEndDateFilter();
	}

	public String createEmplActiveStatusFilter() {
		return format(EQUAL_FILTER_PATTERN, IPeopleField.STATUS.getName(),
				EMPLOYMENT_ACTIVE_STATUS);
	}

	public String createEmplAvailableUsernameFilter() {
		return IPeopleField.USERNAME.getName() + format(LIKE, AT);
	}

	private String createEmptyEndDateFilter() {
		var endDateField = IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleField.END_DATE.getName();
		return format(INSIDE_PARENTHESIS_PATTERN, endDateField + EQUAL + null + OR + format(DATE_GREATER_PATTERN,
				endDateField, LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))));
	}

	@Override
	protected String addDefaultFilter(String filter) {
		var filterByEmail = createEmplAvailableUsernameFilter();
		var filterByStatusActive = createEmplActiveStatusFilter();
		var filterByEmptyEndDate = createEmptyEndDateFilter();
		var defaultFilter = filterByEmail + AND + filterByStatusActive + AND + filterByEmptyEndDate;
		if (StringUtils.isNotBlank(filter)) {
			defaultFilter = defaultFilter + AND + filter;
		}
		return defaultFilter;
	}

	public IPeopleUser findByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return null;
		}
		var emailFilter = IPeopleNavFields.EMP_INFO.getName() + NAVIGATION_SEPARATOR + IPeopleNavFields.PERSON.getName()
				+ NAVIGATION_SEPARATOR + IPeopleNavFields.EMAIL.getName() + NAVIGATION_SEPARATOR
				+ IPeopleField.EMAIL_ADDRESS.getName();
		var result = findByFilter(format(EQUAL_FILTER_PATTERN, format(LOWER_CASE_FILTER, emailFilter),
				StringUtils.lowerCase(email)), null);
		return CollectionUtils.isEmpty(result) ? null : result.get(0);
	}

	@Override
	protected List<SFODataUser> getCacheEntities() {
		return getCacheBean().findInCache(IPeopleCacheBean.generateServiceEntityClassKey(getClass().getTypeName()),
				SFODataUser.class);
	}
}
