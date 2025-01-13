package com.axonivy.utils.gdprconnector.datadeletion.service;

import static com.axonivy.utils.gdprconnector.Constants.COMMA;
import static  com.axonivy.utils.gdprconnector.Constants.DOT;
import static  com.axonivy.utils.gdprconnector.enums.CustomField.DATA_DELETED_BY_FY;
import static  com.axonivy.utils.gdprconnector.enums.CustomField.ENTITY_ID;
import static  com.axonivy.utils.gdprconnector.enums.CustomField.LEGAL_ENTITY_CODE;
import static  com.axonivy.utils.gdprconnector.utils.DateUtils.convertStringToLocalDate;
import static  com.axonivy.utils.gdprconnector.utils.IvyVariableUtils.getIntFromVariable;
import static  com.axonivy.utils.gdprconnector.utils.IvyVariableUtils.getStringFromVariable;
import static  com.axonivy.utils.gdprconnector.utils.IvyVariableUtils.splitIvyVariableValuesToList;
import static java.lang.String.valueOf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import  com.axonivy.utils.gdprconnector.datadeletion.bo.DataDeletionMessage;
import  com.axonivy.utils.gdprconnector.datadeletion.service.DataDeletionService;
import  com.axonivy.utils.gdprconnector.enums.CategoryPath;
import  com.axonivy.utils.gdprconnector.enums.CustomField;
import  com.axonivy.utils.gdprconnector.enums.IvyVariable;
import  com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import  com.axonivy.utils.gdprconnector.service.FinancialYearService;
import  com.axonivy.utils.gdprconnector.utils.DateUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.FilterLink;

public class DataDeletionService {
	public static final String TICKETING_CATEGORY = "ticketing";
	public static final String KNOWLEDGE_BASE_CATEGORY = "knowledgeBase";
	public static final String ONBOARDING_CATEGORY = "onboarding";
	public static final String WORKFORCE_ADMIN_CATEGORY = "workforceAdministration";
	private static final String FY_PREFIX = "FY";
	private static final int START_INDEX = 0;
	public static final int PAGE_SIZE = 1000;

	private static DataDeletionService instance;

	private DataDeletionService() {
	}

	public static DataDeletionService getInstance() {
		if (instance == null) {
			instance = new DataDeletionService();
		}
		return instance;
	}

	public List<ICase> queryTop1000CasesByFinancialYear(FinancialYear financialYear) {
		if (financialYear == null) {
			return List.of();
		}
		return Sudo.get(() -> {
			var caseQuery = buildFinancialCaseQuery(financialYear);
			return caseQuery.executor().results(START_INDEX, PAGE_SIZE);
		});
	}

	public long countByFinancialYear(FinancialYear financialYear) {
		if (financialYear == null) {
			return 0l;
		}
		return Sudo.get(() -> {
			var caseQuery = buildFinancialCaseQuery(financialYear);
			return caseQuery.executor().count();
		});
	}

	private CaseQuery buildFinancialCaseQuery(FinancialYear financialYear) {
		var legalEnttiesQuery = CaseQuery.create();
		for (var legal : getLegalEntitiesInScope()) {
			queryLegalEntityCustomField(legalEnttiesQuery, legal.trim());
		}
		var caseQuery = CaseQuery.businessCases();
		caseQuery.where().and(legalEnttiesQuery);
		return caseQuery.where().and().startTimestamp()
				.isGreaterOrEqualThan(DateUtils.getDate(financialYear.getStartFY())).and().startTimestamp()
				.isLowerOrEqualThan(DateUtils.getDate(financialYear.getEndFY()))
				.and(queryEntityIdNotNullAndNotInFYDataDeleted()).orderBy().category();
	}

	private FilterLink queryLegalEntityCustomField(CaseQuery query, String legalEntity) {
		return query.where().or().customField().stringField(LEGAL_ENTITY_CODE.getFieldName()).isEqual(legalEntity);
	}

	private FilterLink queryEntityIdNotNullAndNotInFYDataDeleted() {
		return CaseQuery.create().where().and().customField().stringField(ENTITY_ID.getFieldName()).isNotNull().and()
				.customField().stringField(DATA_DELETED_BY_FY.getFieldName()).isNull();
	}

//	public void unifyDataForCases() {
//		long totalMissingCases = countMissingCases();
//		List<ICase> missingLECases = new ArrayList<>();
//		do {
//			missingLECases = findTop1000MissingCases();
//			for (var unifiedCase : missingLECases) {
//				var entityId = extractEntityIdValueFromCase(unifiedCase);
//				if (StringUtils.isBlank(entityId)) {
//					continue;
//				}
//				FoObject legalEntity = null;
//				if (unifiedCase.getCategoryPath().startsWith(TICKETING_CATEGORY)) {
//					legalEntity = findLegalEntityForTicketing(entityId);
//				} else if (unifiedCase.getCategoryPath().startsWith(ONBOARDING_CATEGORY)) {
//					legalEntity = findLegalEntityForOnboarding(entityId);
//				} else if (unifiedCase.getCategoryPath().startsWith(WORKFORCE_ADMIN_CATEGORY)) {
//					legalEntity = findLegalEntityForWorkforceAdmin(entityId);
//				}
//				if (legalEntity != null) {
//					LegalEntityCustomFieldUtils.addLegalEntityCustomFieldForCase(unifiedCase, legalEntity);
//				}
//			}
//		} while (CollectionUtils.isNotEmpty(missingLECases) && totalMissingCases > PAGE_SIZE);
//	}

//	private FoObject findLegalEntityForWorkforceAdmin(String entityId) {
//		// Absence Management
//		var leaveRequest = LeaveRequestDAO.getInstance().findById(entityId);
//		if (leaveRequest != null) {
//			return leaveRequest.getLegalEntity();
//		}
//		// Imagine
//		var imagineChangeAward = ImagineChangeAwardDAO.getInstance().findById(entityId);
//		if (imagineChangeAward != null) {
//			return imagineChangeAward.getLegalEntity();
//		}
//		// Internal Offer
//		var internalOffer = InternalOfferDAO.getInstance().findById(entityId);
//		if (internalOffer != null) {
//			return internalOffer.getLegalEntity();
//		}
//		// Org Change Request
//		var orgChangeRequest = OrganisationalChangeRequestDAO.getInstance().findById(entityId);
//		if (orgChangeRequest != null) {
//			return orgChangeRequest.getLegalEntity();
//		}
//		// Position Maintenance Request
//		var positionMaintenance = PositionMaintenanceRequestDAO.getInstance().findById(entityId);
//		if (positionMaintenance != null) {
//			return Objects.isNull(positionMaintenance.getCurrentLegalEntity()) ? positionMaintenance.getNewLegalEntity()
//					: positionMaintenance.getCurrentLegalEntity();
//		}
//		// VoluntarySeparation
//		var voluntarySeparation = VoluntarySeparationDAO.getInstance().findById(entityId);
//		if (voluntarySeparation != null) {
//			return voluntarySeparation.getLegalEntity();
//		}
//		// NewHireDataRequest
//		var newHireDataRequest = NewHireDataRequestDAO.getInstance().findById(entityId);
//		if (newHireDataRequest != null) {
//			return newHireDataRequest.getLegalEntity();
//		}
//		// WorkerJobDataRequest
//		var workerJobData = WorkerJobDataRequestDAO.getInstance().findById(entityId);
//		if (workerJobData != null) {
//			return Objects.isNull(workerJobData.getCurrentLegalEntity()) ? workerJobData.getNewLegalEntity()
//					: workerJobData.getCurrentLegalEntity();
//		}
//		// WorkerPersonalDataRequest
//		var workerPersonalDataRequest = WorkerPersonalDataRequestDAO.getInstance().findById(entityId);
//		if (workerPersonalDataRequest != null) {
//			return workerPersonalDataRequest.getLegalEntity();
//		}
//		return null;
//	}
//
//	private FoObject findLegalEntityForOnboarding(String entityId) {
//		// Screening
//		var preHireIdentity = PreHireIdentityDAO.getInstance().findById(entityId);
//		if (preHireIdentity != null) {
//			return preHireIdentity.getLegalEntity();
//		}
//		// Logistics
//		var provisioning = ProvisioningDAO.getInstance().findById(entityId);
//		if (provisioning != null) {
//			return provisioning.getLegalEntity();
//		}
//		// Pre-hire onboarding
//		var preHireOnboarding = PreHireOnboardingDAO.getInstance().findById(entityId);
//		if (preHireOnboarding != null) {
//			return preHireOnboarding.getLegalEntity();
//		}
//		// Reschedule - No show
//		var rescheduleRequest = OnboardingRescheduleRequestDAO.getInstance().findById(entityId);
//		if (rescheduleRequest != null) {
//			return rescheduleRequest.getLegalEntity();
//		}
//
//		// 2nd day onboarding: 2 entities of manager & new hire survey share the same
//		// value of legal entity
//		if (isEntityIdListOfSecondDayOnboarding(entityId)) {
//			for (String childEnityId : entityId.split(COMMA)) {
//				return getLegalEntityFromSecondayOnboarding(childEnityId);
//			}
//		} else {
//			return getLegalEntityFromSecondayOnboarding(entityId);
//		}
//		return null;
//	}
//
//	private FoObject getLegalEntityFromSecondayOnboarding(String entityId) {
//		var managerSurvey = ManagerRecruitmentSurveyDAO.getInstance().findById(entityId);
//		if (managerSurvey != null) {
//			return managerSurvey.getLegalEntity();
//		}
//
//		var employeeSurvey = NewHireExperienceSurveyDAO.getInstance().findById(entityId);
//		if (employeeSurvey != null) {
//			return employeeSurvey.getLegalEntity();
//		}
//		return null;
//	}

	public boolean isEntityIdListOfSecondDayOnboarding(String entityId) {
		return StringUtils.isNotBlank(entityId) && entityId.contains(COMMA);
	}

//	private FoObject findLegalEntityForTicketing(String entityId) {
//		var ticketRequest = HRTicketRequestDAO.getInstance().findById(entityId);
//		if (ticketRequest != null && StringUtils.isNotBlank(ticketRequest.getRaiserId())) {
//			IPeoplePerson person = (new IPeoplePersonService()).findByExternalId(ticketRequest.getRaiserId());
//			if (person != null) {
//				return IPeopleUtils.findLegalEntityByEmail(person.getEmail());
//			}
//		}
//		return null;
//	}

	public boolean shouldCreateNewCase() {
		if (hasRunningCase()) {
			return false;
		}

		var initialFinancialYear = getInitialFinancialYear();
		List<Integer> financialYears = new ArrayList<>();
		for (int index = 0; index <= getLastFinancialYearCanBeSelected() - initialFinancialYear; index++) {
			financialYears.add(initialFinancialYear + index);
		}
		return !ListUtils.removeAll(financialYears, getAllProcessedFinancialYears()).isEmpty();
	}

	private boolean hasRunningCase() {
		return Sudo.get(() -> {
			var stateQuery = CaseQuery.create();
			for (var state : CaseState.ACTIVE_STATES) {
				stateQuery.where().or().state().isEqual(state);
			}
			var existingCase = CaseQuery.businessCases().where().category()
					.isEqual(CategoryPath.DATA_DELETION.getPath()).and(stateQuery).executor().firstResult();
			return existingCase != null;
		});
	}

	public List<Integer> getAllProcessedFinancialYears() {
		return FinancialYearService.getInstance().findAllProcessedFinancialYears().stream().map(FinancialYear::getYear)
				.map(Long::intValue).toList();
	}

	public String extractEntityIdValueFromCase(ICase requestCase) {
		return Sudo.get(() -> {
			return requestCase.customFields().stringField(ENTITY_ID.getFieldName()).getOrNull();
		});
	}

	public List<ICase> findTop1000MissingCases() {
		return buildMissingLegalEntityCodeCaseQuery().executor().results(START_INDEX, PAGE_SIZE);
	}

	public long countMissingCases() {
		return buildMissingLegalEntityCodeCaseQuery().executor().count();
	}

	private CaseQuery buildMissingLegalEntityCodeCaseQuery() {
		return CaseQuery.businessCases().where().customField().stringField(LEGAL_ENTITY_CODE.getFieldName()).isNull()
				.and(queryEntityIdNotNullAndNotInFYDataDeleted());
	}

	public void refreshAvailableFinancialYears(List<FinancialYear> financialYears) {
		if (financialYears == null) {
			financialYears = new ArrayList<>();
		}
		var processedFinancialYears = FinancialYearService.getInstance().findAllProcessedFinancialYears();
		if (CollectionUtils.isEmpty(processedFinancialYears)) {
			financialYears = buildLastThreeFinancialYearsFromNow(List.of(), financialYears);
		} else {
			var processedYears = processedFinancialYears.stream().map(FinancialYear::getYear).map(Long::intValue)
					.toList();
			financialYears = buildLastThreeFinancialYearsFromNow(processedYears, financialYears);
		}
	}

	private List<FinancialYear> buildLastThreeFinancialYearsFromNow(List<Integer> processedYears,
			List<FinancialYear> financialYears) {
		String startDateFY = getStartDateFinancialYear();
		int maxTotalFYCanBeSelected = getMaxTotalFinancialYearCanBeSelected();
		int initialFY = getInitialFinancialYear();
		var lastFYCanBeSelected = getLastFinancialYearCanBeSelected();
		var enoughtData = false;
		var startIndex = 0;
		do {
			var year = lastFYCanBeSelected - startIndex;
			enoughtData = year < initialFY || financialYears.size() == maxTotalFYCanBeSelected;
			if (processedYears.contains(year) || enoughtData) {
				startIndex++;
				continue;
			}

			var financialYear = new FinancialYear();
			financialYear.setYear(Long.valueOf(year));
			LocalDate startDate = convertStringToLocalDate(startDateFY.concat(DOT).concat(valueOf(year - 1)));
			financialYear.setStartFY(startDate);
			financialYear.setEndFY(startDate.plusYears(1).minusDays(1));
			financialYear.setName(FY_PREFIX.concat(valueOf(year)));
			financialYears.add(financialYear);
			startIndex++;
		} while (!enoughtData);

		return financialYears;
	}

	public String getDeletionMessageForCurrentCase() {
		return Sudo.get(() -> {
			var messageId = Ivy.wfCase().customFields().stringField(CustomField.DATA_DELETION_MESSAGE_ID.getFieldName())
					.getOrNull();
			var result = Ivy.repo().find(messageId, DataDeletionMessage.class);
			return Objects.isNull(result) ? StringUtils.EMPTY : result.getMessage();
		});
	}

	public String saveDeletionMessageToCurrentCase(String deletionMessage) {
		return Sudo.get(() -> {
			var dataDeletionMessage = new DataDeletionMessage();
			dataDeletionMessage.setCaseId(Ivy.wfCase().getId());
			dataDeletionMessage.setMessage(deletionMessage);
			var messageId = Ivy.repo().save(dataDeletionMessage).getId();
			Ivy.wfCase().customFields().stringField(CustomField.DATA_DELETION_MESSAGE_ID.getFieldName()).set(messageId);
			return messageId;
		});
	}

	public int getLastFinancialYearCanBeSelected() {
		return LocalDateTime.now().getYear() - getMinRangeAfterEndOfFinancialYear();
	}

	public int getMinRangeAfterEndOfFinancialYear() {
		return getIntFromVariable(IvyVariable.DATA_DELETION_MIN_RANGE_AFTER_END_OF_FINANCIAL_YEAR, 2);
	}

	public int getInitialFinancialYear() {
		return getIntFromVariable(IvyVariable.DATA_DELETION_INITIAL_FINANCIAL_YEAR, 2022);
	}

	public String getStartDateFinancialYear() {
		return getStringFromVariable(IvyVariable.DATA_DELETION_START_DATE_FINANCIAL_YEAR);
	}

	public int getMaxTotalFinancialYearCanBeSelected() {
		return getIntFromVariable(IvyVariable.DATA_DELETION_MAX_TOTAL_FINANCIAL_YEAR_CAN_BE_SELECTED, 3);
	}

	public List<String> getLegalEntitiesInScope() {
		return splitIvyVariableValuesToList(IvyVariable.DATA_DELETION_LEGAL_ENTITIES_IN_SCOPE);
	}
}
