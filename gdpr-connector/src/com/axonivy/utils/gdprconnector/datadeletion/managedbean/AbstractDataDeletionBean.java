package com.axonivy.utils.gdprconnector.datadeletion.managedbean;

import static com.axonivy.utils.gdprconnector.service.IvyService.getTranslateStringFromCms;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;

import com.axonivy.utils.gdprconnector.Constants;
import com.axonivy.utils.gdprconnector.datadeletion.contentstate.DataDeletionContentState;
import com.axonivy.utils.gdprconnector.datadeletion.service.DataDeletionService;
import com.axonivy.utils.gdprconnector.enums.CustomField;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialDataDeletion;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import com.axonivy.utils.gdprconnector.service.FinancialDataDeletionService;
import com.axonivy.utils.gdprconnector.utils.FacesContexts;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

public abstract class AbstractDataDeletionBean {
	protected static final String LOGIC_CLOSE_METHOD = "#{logic.close}";
	protected List<FinancialYear> financialYears;
	protected List<FinancialYear> selectedFinancialYears;
	protected FinancialDataDeletion  financialDataDeletion;
	protected DataDeletionContentState contentState;

	public void init() {
		contentState = getContentStateToInit();
		selectedFinancialYears = new ArrayList<>();
		initFinancialDataDeletion();
	}

	private void initFinancialDataDeletion() {
		String requestId = FacesContexts.evaluateValueExpression("#{data.financialDataDeletionId}", String.class);
		financialDataDeletion = getFinancialDataDeletionById(requestId);
		if (financialDataDeletion == null) {
			financialDataDeletion = new FinancialDataDeletion();
			financialDataDeletion.setFinancialYears(new ArrayList<>());
		} else {
			selectedFinancialYears.addAll(financialDataDeletion.getFinancialYears());
		}
	}

	protected FinancialDataDeletion getFinancialDataDeletionById(String requestId) {
		return FinancialDataDeletionService.getInstance().findByIdWithRelatedData(requestId);
	}

	protected abstract DataDeletionContentState getContentStateToInit();

	protected ICustomStringField getDeletedFinancialYearsCustomField() {
		return Sudo.get(() -> {
			return Ivy.wfTask().customFields().stringField(CustomField.DELETED_FINANCIAL_YEARS.getFieldName());
		});
	}

	public String getFinancialYearLabel(FinancialYear financialYear) {
		if (financialYear == null) {
			return EMPTY;
		} else if (BooleanUtils.isTrue(financialYear.getIsProcessed())) {
			return String.format(Constants.NAME_AND_CODE_FORMAT, financialYear.getName(), getTranslateStringFromCms(
					"/Dialogs/com/ricoh/hr/core/DataDeletion/ProcessStatusMessage/DataDeleted"));
		}
		return financialYear.getName();
	}

	public String getTotalRecordsDeletedMessage() {
		var financialYears = financialDataDeletion.getFinancialYears();
		return getTranslateStringFromCms("/Dialogs/com/ricoh/hr/core/DataDeletion/DataDeletionCompletedHelpText",
				financialYears.stream().map(FinancialYear::getNumberOfTasks).mapToLong(Long::longValue).sum(),
				financialYears.stream().map(FinancialYear::getNumberOfCases).mapToLong(Long::longValue).sum());
	}
	
	protected void initFinancialYears() {
		financialYears = new ArrayList<>();
		financialYears.addAll(Optional.ofNullable(financialDataDeletion)
				.map(FinancialDataDeletion::getFinancialYears).orElse(List.of()));
				
	}

	public List<FinancialYear> getFinancialYears() {
		if (CollectionUtils.isEmpty(financialYears)) {
			initFinancialYears();
			DataDeletionService.getInstance().refreshAvailableFinancialYears(financialYears);
		}
		return financialYears;
	}

	public void setFinancialYears(List<FinancialYear> financialYears) {
		this.financialYears = financialYears;
	}

	public DataDeletionContentState getContentState() {
		return contentState;
	}

	public void setContentState(DataDeletionContentState contentState) {
		this.contentState = contentState;
	}

	public FinancialDataDeletion getFinancialDataDeletion() {
		return financialDataDeletion;
	}

	public void setFinancialDataDeletion(FinancialDataDeletion financialDataDeletion) {
		this.financialDataDeletion = financialDataDeletion;
	}

	public List<FinancialYear> getSelectedFinancialYears() {
		return selectedFinancialYears;
	}

	public void setSelectedFinancialYears(List<FinancialYear> selectedFinancialYears) {
		this.selectedFinancialYears = selectedFinancialYears;
	}
}
