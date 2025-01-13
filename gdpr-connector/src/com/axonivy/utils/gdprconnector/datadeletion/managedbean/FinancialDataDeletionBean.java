package com.axonivy.utils.gdprconnector.datadeletion.managedbean;

import static com.axonivy.utils.gdprconnector.Constants.COMMA;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;
import javax.transaction.TransactionRolledbackException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PF;

import com.axonivy.utils.gdprconnector.datadeletion.contentstate.DataDeletionContentState;
import com.axonivy.utils.gdprconnector.datadeletion.service.DataDeletionService;
import com.axonivy.utils.gdprconnector.enums.CustomField;
import com.axonivy.utils.gdprconnector.enums.DataDeletionState;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialCaseInfo;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import com.axonivy.utils.gdprconnector.service.FinancialDataDeletionService;
import com.axonivy.utils.gdprconnector.service.FinancialYearService;
import com.axonivy.utils.gdprconnector.utils.FacesContexts;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class FinancialDataDeletionBean  extends AbstractDataDeletionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ENABLE_ACTION_BUTTONS_RC = "enableActionButtonsRC()";
	private static final String NEW_LINE = "\n";
	private StringBuilder deletingMessage;
	private boolean isDeletionSuccessful;
	private boolean canBeFinishedTask;

	@Override
	@PostConstruct
	public void init() {
		super.init();
		canBeFinishedTask = getDeletedFinancialYearsCustomField().getOrNull() != null;
		if (canBeFinishedTask) {
			contentState.setReadOnly(true);
			updateContentForSubmittingDeletion();
		}
		deletingMessage = new StringBuilder(DataDeletionService.getInstance().getDeletionMessageForCurrentCase());
	}

	public void confirmDeleteData() {
		contentState.setInProgressMessageVisible(true);
		updateContentForSubmittingDeletion();
		isDeletionSuccessful = false;
		deletingMessage = new StringBuilder();
	}

	private void updateContentForSubmittingDeletion() {
		contentState.setDeletionStatusVisible(true);
		contentState.setSelectFinancialYearReadOnly(true);
		contentState.setCloseButtonVisible(false);
		contentState.setSubmitButtonVisible(false);
		contentState.setFinishButtonVisible(true);
	}

	public void deleteData() throws InterruptedException, TransactionRolledbackException {
		Set<String> deletedIds = new HashSet<>();
		var deletedFYs = EMPTY;
		addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/StartProcess");
		for (FinancialYear financialYear : CollectionUtils.emptyIfNull(selectedFinancialYears)) {
			financialYear.setNumberOfTasks(0l);
			financialYear.setNumberOfCases(0l);
			financialYear.setFinancialCaseInfos(new ArrayList<>());
			financialYear.setIsProcessed(true);
			financialYear.setFinancialDataDeletion(financialDataDeletion);
			financialYear = FinancialYearService.getInstance().save(financialYear);
			addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/FindingCasesOfFinancialYear",
					financialYear.getName(), financialYear.getStartFY(), financialYear.getEndFY());
			long totalFinancialCases = DataDeletionService.getInstance().countByFinancialYear(financialYear);
			if (totalFinancialCases > 0) {
				addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/CheckAndDeleteEntities");
				checkAndDeteleEntities(deletedIds, financialYear, totalFinancialCases);
			}
			deletedFYs = deletedFYs.concat(COMMA).concat(financialYear.getName());
		}
		getDeletedFinancialYearsCustomField().set(deletedFYs);
		canBeFinishedTask = true;
		if (deletedIds.size() == 0) {
			addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/NoDataToDelete");
		} else {
			addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/DeletedEntitiesInDB",
					deletedIds.size());
		}
		addNewMessage("/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/ProcessStatusMessage/Finished");

		isDeletionSuccessful = true;
		contentState.setInProgressMessageVisible(false);
//		financialDataDeletion = FinancialDataDeletionService.getInstance()
//				.findByIdWithRelatedData(financialDataDeletion.getId());
		DataDeletionService.getInstance().saveDeletionMessageToCurrentCase(getDeletingMessage());
	}

	private void checkAndDeteleEntities(Set<String> deletedIds, FinancialYear financialYear, long totalFinancialCases)
			throws TransactionRolledbackException {
//		List<ICase> financialCases = new ArrayList<>();
//		do {
//			List<FinancialCaseInfo> financialCaseInfoList = new ArrayList<>();
//			financialCases = DataDeletionService.getInstance().queryTop1000CasesByFinancialYear(financialYear);
//			for (var deletedCase : financialCases) {
//				var isCaseDestroyed = false;
//				var financialCaseInfo = new FinancialCaseInfo();
//				financialCaseInfo.setFinancialYear(financialYear);
//				String entityId = deleteEntitiesOnRicohHRBusinessDataDB(deletedIds, deletedCase, financialCaseInfo);
//				if (deletedCase.getState() != CaseState.DONE && deletedCase.getState() != CaseState.DESTROYED) {
//					addNewMessage("/Dialogs/com/ricoh/hr/core/DataDeletion/ProcessStatusMessage/DestroyedCase",
//							deletedCase.getId(), deletedCase.getName());
//					IvyUtils.destroyCase(deletedCase);
//					isCaseDestroyed = true;
//				}
//				if (deletedIds.contains(entityId) || isCaseDestroyed) {
//					List<ITask> allTasksOfDeletedCase = getAllTasksOfCase(deletedCase);
//					String deletedTaskId = extractDeletedTaskIds(allTasksOfDeletedCase);
//					financialCaseInfo.setCaseId(deletedCase.getId());
//					financialCaseInfo.setTaskNumbers(deletedTaskId);
//					if (StringUtils.isEmpty(financialCaseInfo.getResult())) {
//						financialCaseInfo.setResult(getTranslateStringFromCms(
//								"/Dialogs/com/ricoh/hr/core/DataDeletion/ProcessStatusMessage/DataDeleted"));
//					}
//					financialCaseInfoList.add(financialCaseInfo);
//
//					updateNoteAndCustomfieldForDeletedCase(financialYear.getName(), deletedCase, deletedTaskId);
//					// Update count for FY
//					financialYear.setNumberOfTasks(financialYear.getNumberOfTasks() + allTasksOfDeletedCase.size());
//					financialYear.setNumberOfCases(financialYear.getNumberOfCases() + 1);
//				}
//			}
//			FinancialCaseInfoDAO.getInstance().saveAll(financialCaseInfoList);
//		} while (CollectionUtils.isNotEmpty(financialCases) && totalFinancialCases > DataDeletionService.PAGE_SIZE);
//		financialYear = FinancialYearService.getInstance().save(financialYear);
	}

	private String extractDeletedTaskIds(List<ITask> tasks) {
		return Sudo.get(() -> {
			return CollectionUtils.emptyIfNull(tasks).stream().map(ITask::getId).map(Object::toString)
					.collect(Collectors.joining(COMMA));
		});
	}

	private List<ITask> getAllTasksOfCase(ICase selectedCase) {
		return Sudo.get(() -> {
			return selectedCase.tasks().all();
		});
	}

	private void updateNoteAndCustomfieldForDeletedCase(String financialYearName, ICase deletedCase,
			String deletedTaskId) {
		Sudo.run(() -> {
			deletedCase.customFields().stringField(CustomField.DATA_DELETED_BY_FY.getFieldName())
					.set(financialYearName);
			deletedCase.createNote(Ivy.session(), Ivy.cms().co(
					"/Dialogs/com/axonivy/utils/gdprconnector/DataDeletion/DeletedCaseNoteMessage", List.of(deletedTaskId)));
		});
	}

	private String deleteEntitiesOnRicohHRBusinessDataDB(Set<String> deletedIds, ICase existedCase,
			FinancialCaseInfo dataDeletionCaseInfo) {
		return null;
	}

	private void deleteDataForWorkforceAdmin(Set<String> deletedIds, String entityId) {}

	private void deleteDataForOnboarding(Set<String> deletedIds, String entityId) {}

	private void deleteDataForSecondDayOnboarding(Set<String> deletedIds, String entityId) {}

	private void deleteDataForTicketingAndKnowledgeBase(Set<String> deletedIds, String entityId) {}

	public void finishTask() {
		Sudo.run(() -> {
			Ivy.wfCase().customFields().stringField(CustomField.HIGHEST_STATE.getFieldName())
					.set(DataDeletionState.BUSINESS_DETAILS.name());
		});
		FacesContexts.invokeMethodByExpression(LOGIC_CLOSE_METHOD, new Object[] {}, null);
	}

	private void addNewMessage(String message, Object... params) {
		deletingMessage.append(NEW_LINE);
		if (params == null || params.length == 0) {
			deletingMessage.append(getTranslateStringFromCms(message));
			return;
		}
		deletingMessage.append(getTranslateStringFromCms(message, params));
	}

	private Object getTranslateStringFromCms(String message, String... params) {
		// TODO Auto-generated method stub
		return Ivy.cms().co(message, params);
	}

	public String getDeletingMessage() {
		if (deletingMessage == null) {
			return EMPTY;
		}
		return deletingMessage.toString();
	}

	public boolean isCanBeFinishedTask() {
		return canBeFinishedTask;
	}

	public boolean stopPollMessage() {
		if (isDeletionSuccessful || contentState.isReadOnly()) {
			PF.current().executeScript(ENABLE_ACTION_BUTTONS_RC);
			return true;
		}
		return false;
	}

	@Override
	protected DataDeletionContentState getContentStateToInit() {
		return new DataDeletionContentState(DataDeletionState.DATA_DELETION);
	}
}
