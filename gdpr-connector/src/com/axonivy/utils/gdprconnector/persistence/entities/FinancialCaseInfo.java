package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CASE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_CASE_INFO;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_YEAR_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_FINANCIAL_YEAR_CASE_INFO;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NOTE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.RESULT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.TASK_NUMBERS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = FINANCIAL_CASE_INFO)
public class FinancialCaseInfo extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = CASE_ID)
	private Long caseId;

	@Column(name = TASK_NUMBERS)
	private String taskNumbers;

	@Column(name = RESULT)
	private String result;

	@Column(name = NOTE)
	private String note;

	@ManyToOne
	@JoinColumn(name = FINANCIAL_YEAR_ID, foreignKey = @ForeignKey(name = FK_FINANCIAL_YEAR_CASE_INFO))
	private FinancialYear financialYear;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getTaskNumbers() {
		return taskNumbers;
	}

	public void setTaskNumbers(String taskNumbers) {
		this.taskNumbers = taskNumbers;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
}
