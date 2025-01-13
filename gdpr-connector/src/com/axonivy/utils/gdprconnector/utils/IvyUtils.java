package com.axonivy.utils.gdprconnector.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.ricoh.hr.core.enums.CustomField;
import com.ricoh.hr.core.persistence.entities.OrganisationalChangeRequest;
import com.ricoh.hr.core.persistence.entities.PositionMaintenanceRequest;
import com.ricoh.hr.core.persistence.entities.PreHireIdentity;
import com.ricoh.hr.core.persistence.entities.VoluntarySeparation;
import com.ricoh.hr.core.persistence.entities.WorkerJobDataRequest;
import com.ricoh.hr.core.persistence.entities.WorkerPersonalDataRequest;
import com.ricoh.hr.core.service.IvyService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.FilterLink;

public class IvyUtils {
	private static final String SYSTEM = "SYSTEM";
	private static final String HIDE_PROPERTY = "HIDE";
	private static final String SEARCH_LIKE_VALUE = "%%%s%%";

	private IvyUtils() {
	}

	public static ICase findCaseBy(VoluntarySeparation voluntarySeparation) {
		return findCaseBy(voluntarySeparation.getId());
	}

	public static ICase findCaseBy(OrganisationalChangeRequest organisationalChangeRequest) {
		return findCaseBy(organisationalChangeRequest.getId());
	}

	public static ICase findCaseBy(WorkerJobDataRequest workerJobDataRequest) {
		return findCaseBy(workerJobDataRequest.getId());
	}

	public static ICase findCaseBy(WorkerPersonalDataRequest workerPersonalDataRequest) {
		return findCaseBy(workerPersonalDataRequest.getId());
	}

	public static ICase findCaseBy(PositionMaintenanceRequest positionMaintenanceRequest) {
		return findCaseBy(positionMaintenanceRequest.getId());
	}

	public static ICase findCaseBy(PreHireIdentity preHireIdentity) {
		return findCaseBy(preHireIdentity.getId());
	}

	public static ICase findCaseBy(String id) {
		return Sudo.get(() -> {
			CaseQuery caseQuery = CaseQuery.create().where().customField()
					.stringField(CustomField.ENTITY_ID.getFieldName()).isEqual(id);
			return Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery);
		});
	}

	public static ICase findSubCaseBy(String id) {
		return Sudo.get(() -> {
			CaseQuery caseQuery = CaseQuery.subCases().where().customField()
					.stringField(CustomField.ENTITY_ID.getFieldName()).isEqual(id);
			return Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery);
		});
	}

	public static Long findCaseIdBy(VoluntarySeparation voluntarySeparation) {
		ICase iCase = findCaseBy(voluntarySeparation);
		return iCase != null ? iCase.getId() : Ivy.wfCase().getId();
	}

	public static Long findCaseIdBy(PreHireIdentity preHireIdentity) {
		ICase iCase = findCaseBy(preHireIdentity);
		return iCase != null ? iCase.getId() : Ivy.wfCase().getId();
	}

	public static IUser findUserByUserName(String userName) {
		return Sudo.get(() -> {
			IUser userFromSystem = Ivy.wf().getSecurityContext().users().findWithExternalLookup(userName);
			if (userFromSystem != null && userFromSystem.getProperty(HIDE_PROPERTY) == null
					&& !userFromSystem.getName().equals(String.join("#", SYSTEM))) {
				return userFromSystem;
			} else {
				return null;
			}
		});
	}

	public static String generateDisplayNameForInactiveUser(String username) {
		return StringUtils.join(username, StringUtils.SPACE, Ivy.cms().co("/Labels/InactiveUserHelperText"));
	}

	public static boolean isTaskReadyForProcess() {
		return Ivy.wfTask().getState() == TaskState.CREATED || (Ivy.wfTask().getState() == TaskState.RESUMED
				&& Ivy.session().getSessionUser().equals(Ivy.wfTask().getWorkerUser()));
	}

	public static void resetTask() {
		Sudo.run(() -> {
			Ivy.wfTask().reset();
		});
	}

	public static void destroyCase(ICase iCase) {
		Sudo.run(() -> {
			iCase.destroy();
		});
	}

	public static void resetTaskAsNeeded() {
		if (isTaskReadyForProcess()) {
			resetTask();
		}
	}

	public static boolean hasRunningCases(CustomField customField, Long value) {
		if (BooleanUtils.toBoolean(Ivy.var().get("restrictedEntity.developerOptions.skipCheckRunningCase"))) {
			return false;
		}
		List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(getRunningCasesQuery(customField, value));
		return CollectionUtils.isNotEmpty(cases);
	}

	private static CaseQuery getRunningCasesQuery(CustomField customField, Long value) {
		return CaseQuery.create().where().customField().numberField(customField.getFieldName()).isEqual(value).and()
				.state().isNotEqual(CaseState.DONE).and().state().isNotEqual(CaseState.DESTROYED).and().state()
				.isNotEqual(CaseState.ZOMBIE);
	}

	public static boolean hasDoneOrRunningCases(CustomField customField, Long value) {
		List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(getDoneOrRunningCasesQuery(customField, value));
		return CollectionUtils.isNotEmpty(cases);
	}

	private static CaseQuery getDoneOrRunningCasesQuery(CustomField customField, Long value) {
		return CaseQuery.create().where().customField().numberField(customField.getFieldName()).isEqual(value).and()
				.state().isNotEqual(CaseState.DESTROYED).and().state().isNotEqual(CaseState.ZOMBIE);
	}

	public static boolean isCurrentUserInvolvedInCase(ICase icase) {
		String currentUserName = Ivy.session().getSessionUserName();
		for (ITask task : icase.tasks().all()) {
			if (currentUserName.equals(task.getWorkerUserName())
					|| (task.getActivator() != null && currentUserName.equals(task.getActivator().getName()))) {
				return true;
			}
		}
		return false;
	}

	public static List<ICase> findAllCasesByCategory(String category) {
		return Sudo.get(() -> {
			String categorySearchValue = String.format(SEARCH_LIKE_VALUE, category);
			CaseQuery caseQuery = CaseQuery.create().where().category().isLike(categorySearchValue);
			List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(caseQuery);
			return CollectionUtils.isNotEmpty(cases) ? cases : new ArrayList<>();
		});
	}

	public static String getNoteForDiscardedCase() {
		String discardNote = String.valueOf(Ivy.wfCase().createNote(Ivy.session(), IvyService.getTranslateStringFromCms(
				"/Processes/DiscardDraft/note", Ivy.session().getSessionUser().getDisplayName())));
		return discardNote;
	}

	public static List<ICase> findRunningCasesByCategory(String category) {
		CaseQuery caseQuery = CaseQuery.create().where().category().isLike(category).and().state()
				.isNotEqual(CaseState.DONE).and().state().isNotEqual(CaseState.DESTROYED).and().state()
				.isNotEqual(CaseState.ZOMBIE);
		List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(caseQuery);
		return cases;
	}

	public static FilterLink buildRunningTaskQuery() {
		//@formatter:off
		return TaskQuery.create().where()
				.state().isNotEqual(TaskState.DONE)
				.and().state().isNotEqual(TaskState.DESTROYED)
				.and().state().isNotEqual(TaskState.ZOMBIE);
		//@formatter:on
	}
}
