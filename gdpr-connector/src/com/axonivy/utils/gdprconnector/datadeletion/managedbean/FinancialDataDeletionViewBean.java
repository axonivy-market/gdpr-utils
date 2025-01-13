package com.axonivy.utils.gdprconnector.datadeletion.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;

import com.axonivy.utils.gdprconnector.datadeletion.contentstate.DataDeletionContentState;
import com.axonivy.utils.gdprconnector.enums.DataDeletionState;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialCaseInfo;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialDataDeletion;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import com.axonivy.utils.gdprconnector.service.FinancialDataDeletionService;


public class FinancialDataDeletionViewBean extends AbstractDataDeletionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private FinancialYear targetFinancialYear;
	private int taskCounts;
	private int caseCounts;

	public int getTaskCounts() {
		return taskCounts;
	}

	public void setTaskCounts(int taskCounts) {
		this.taskCounts = taskCounts;
	}

	public int getCaseCounts() {
		return caseCounts;
	}

	public void setCaseCounts(int caseCounts) {
		this.caseCounts = caseCounts;
	}

	public FinancialYear getTargetFinancialYear() {
		return targetFinancialYear;
	}

	public void setTargetFinancialYear(FinancialYear targetFinancialYear) {
		this.targetFinancialYear = targetFinancialYear;
	}

	@Override
	@PostConstruct
	public void init() {
		super.init();
		initTargetFinancialYear();
	}

	@Override
	protected FinancialDataDeletion getFinancialDataDeletionById(String requestId) {
		return FinancialDataDeletionService.getInstance().findByIdWithAllRelatedData(requestId);

	}

	@Override
	protected DataDeletionContentState getContentStateToInit() {
		return new DataDeletionContentState(DataDeletionState.DATA_DELETION);
	}

	public Long getProcessedTasksCount() {
		return Optional.ofNullable(targetFinancialYear).map(FinancialYear::getNumberOfTasks).orElse((long) 0);
	}

	public Long getProcessedCasesCount() {
		return Optional.ofNullable(targetFinancialYear).map(FinancialYear::getNumberOfCases).orElse((long) 0);
	}

	public List<FinancialCaseInfo> getProceedTasks() {
		return Optional.ofNullable(targetFinancialYear).map(FinancialYear::getFinancialCaseInfos)
				.orElse(new ArrayList<FinancialCaseInfo>());
	}

	private void initTargetFinancialYear() {
		if (CollectionUtils.isNotEmpty(financialYears)) {
			targetFinancialYear = financialYears.get(0);
		}
	}
}
