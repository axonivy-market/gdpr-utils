package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AND;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_LOWER_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NAVIGATION_SEPARATOR;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataEmpJob;
import com.axonivy.connector.successfactors.connector.rest.SFODataPicklistOption;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.bo.IPeopleJob;
import com.axonivy.utils.gdprconnector.constant.IPeopleConstants;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.IPeopleNavFields;
import com.axonivy.utils.gdprconnector.enums.IPeopleObjectType;

import ch.ivyteam.ivy.process.call.SubProcessCallResult;

public class IPeopleJobService extends IPeopleAbstractService<IPeopleJob, SFODataEmpJob> {
	private static final int DEFAULT_TOP_PARAM = 1;

	/**
	 * Find operating company for user by their email address
	 *
	 * @param email - email address of user
	 * @return - FoObject with externalId and company name
	 */
	public FoObject findOperatingCompany(String email) {
		String filterQuery = String.format(EQUAL_LOWER_FILTER_PATTERN, IPeopleNavFields.EMPLOYMENT.getName() + NAVIGATION_SEPARATOR
				+ IPeopleNavFields.PERSON.getName() + NAVIGATION_SEPARATOR
				+ IPeopleNavFields.EMAIL.getName() + NAVIGATION_SEPARATOR + IPeopleField.EMAIL_ADDRESS.getName(), email) + AND
				+ getDefaultFilters();

		List<String> select = new ch.ivyteam.ivy.scripting.objects.List<>();
		select.add(IPeopleNavFields.COMPANY.getName() + NAVIGATION_SEPARATOR + IPeopleField.EXTERNAL_CODE.getName());
		select.add(IPeopleNavFields.COMPANY.getName() + NAVIGATION_SEPARATOR + IPeopleField.NAME.getName());

		List<String> expand = new ch.ivyteam.ivy.scripting.objects.List<>();
		expand.add(IPeopleNavFields.COMPANY.getName());

		SubProcessCallResult callResult = callSubWithParams(filterQuery, select, expand, getDefaultOrderby(), null,
				DEFAULT_TOP_PARAM, null);
		SFODataEmpJob job = extractEmpJobData(callResult);
		return job != null ? FoObjectFactory.create(job.getCompanyNav()) : null;
	}

	@Override
	protected IPeopleObjectType getType() {
		return IPeopleObjectType.JOB;
	}

	@Override
	protected String getReturnParam() {
		return "jobs";
	}

	@Override
	protected String getStartName() {
		return "getJobs";
	}

	@Override
	protected IPeopleJob mapResult(SFODataEmpJob value) {
		return IPeopleJobFactory.createJob(value);
	}

	@Override
	protected List<String> getDefaultSelection() {
		return ch.ivyteam.ivy.scripting.objects.List.create(String.class);
	}

	@Override
	protected List<String> getDefaultOrderby() {
		return IPeopleJobServiceFactory.getDefaultOrderby();
	}

	@Override
	protected List<String> getDefaultExpand() {
		return ch.ivyteam.ivy.scripting.objects.List.create(String.class);
	}

	@Override
	protected boolean useActiveFilter() {
		return false;
	}

	public String getEmployeeStatusByPersonalIdExternal(String id) {
		String filterString = IPeopleConstants.EQUAL_FILTER_PATTERN.formatted(IPeopleNavFields.EMPLOYMENT.getName()
				+ NAVIGATION_SEPARATOR + IPeopleField.PERSON_ID_EXTERNAL.getName(), id);
		List<String> select = new ch.ivyteam.ivy.scripting.objects.List<>();
		select.add(
				IPeopleNavFields.EMPL_STATUS.getName() + NAVIGATION_SEPARATOR + IPeopleField.EXTERNAL_CODE.getName());

		List<String> expand = new ch.ivyteam.ivy.scripting.objects.List<>();
		expand.add(IPeopleNavFields.EMPL_STATUS.getName());

		SubProcessCallResult result = callEmpJobProcessByCustomSelectAndExpand(filterString, select, expand);
		SFODataEmpJob job = extractEmpJobData(result);

		return Optional.ofNullable(job).map(SFODataEmpJob::getEmplStatusNav).map(SFODataPicklistOption::getExternalCode)
				.orElse(StringUtils.EMPTY);
	}

	public String findUserIdByPositionCode(String positionCode) {
		SubProcessCallResult callResult = callEmpJobProcessByCustomSelect(createFilterByPositionCode(positionCode),
				IPeopleJobServiceFactory.getUserIdField());
		SFODataEmpJob job = extractEmpJobData(callResult);
		return job != null ? job.getUserId() : "";
	}

	public static String createFilterByPositionCode(String positionCode) {
		return String.format(EQUAL_FILTER_PATTERN, "position", positionCode) + AND + getDefaultFilters();
	}

	public String findUserIdByParentPositionCode(String parentPositionCode) {
		String filter = String.format(EQUAL_FILTER_PATTERN, "positionNav/parentPosition/code", parentPositionCode);
		SubProcessCallResult callResult = callEmpJobProcessByCustomSelect(filter,
				IPeopleJobServiceFactory.getUserIdField());
		SFODataEmpJob job = extractEmpJobData(callResult);
		return job != null ? job.getUserId() : "";
	}

	private SubProcessCallResult callEmpJobProcessByCustomSelect(String filter, List<String> select) {
		return callEmpJobProcessByCustomSelectAndExpand(filter, select, null);
	}

	private SubProcessCallResult callEmpJobProcessByCustomSelectAndExpand(String filter, List<String> select,
			List<String> expand) {
		return callSubWithParams(filter, select, expand, getDefaultOrderby(), null, DEFAULT_TOP_PARAM, null);
	}

	private SFODataEmpJob extractEmpJobData(SubProcessCallResult callResult) {
		if (callResult != null) {
			Optional<Object> result = Optional.ofNullable(callResult.get(getReturnParam()));
			if (result.isPresent() && result.get() instanceof List<?> list) {
				for (Object data : list) {
					if (data instanceof SFODataEmpJob job) {
						return job;
					}
				}
			}
		}
		return null;
	}

	public String findPositionByUserId(String userId) {
		List<String> select = new ch.ivyteam.ivy.scripting.objects.List<>();
		select.add("position");
		String filter = String.format(EQUAL_FILTER_PATTERN, "userId", userId) + AND + getDefaultFilters();
		SubProcessCallResult callResult = callEmpJobProcessByCustomSelect(filter, select);
		SFODataEmpJob job = extractEmpJobData(callResult);
		return job != null ? job.getPosition() : "";
	}

	public IPeopleJob findByJobCode(String jobCode) {
		String filter = String.format(EQUAL_FILTER_PATTERN, IPeopleField.JOB_CODE.getName(), jobCode);
		List<IPeopleJob> result = findByFilter(filter, 1);
		return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
	}

	private static String getDefaultFilters() {
		LocalDate currentDate = LocalDate.now();
		return IPeopleNavFields.EMPLOYMENT.getName() + NAVIGATION_SEPARATOR + IPeopleField.START_DATE.getName()
				+ " le datetime'" + currentDate.atStartOfDay() + "' and (" + IPeopleNavFields.EMPLOYMENT.getName()
				+ NAVIGATION_SEPARATOR + IPeopleField.END_DATE.getName() + " ge datetime'" + currentDate.atStartOfDay()
				+ "' or " + IPeopleNavFields.EMPLOYMENT.getName() + NAVIGATION_SEPARATOR
				+ IPeopleField.END_DATE.getName() + " eq null)";
	}
}
