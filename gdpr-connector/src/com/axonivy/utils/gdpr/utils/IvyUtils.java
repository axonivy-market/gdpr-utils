package com.axonivy.utils.gdpr.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdpr.enums.CustomField;
import com.axonivy.utils.gdpr.service.IvyService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class IvyUtils {
	private static final String SYSTEM = "SYSTEM";
	private static final String HIDE_PROPERTY = "HIDE";
	private static final String SEARCH_LIKE_VALUE = "%%%s%%";

	private IvyUtils() {
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

}
