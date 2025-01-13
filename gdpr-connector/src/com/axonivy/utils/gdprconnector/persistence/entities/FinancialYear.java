package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.END_FY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_DATA_DELETION_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_YEAR_MAPPER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_YEAR_TABLE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_FINANCIAL_DATA_DELETION_FINANCIAL_YEAR;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.IS_PROCESSED;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NAME;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NUMBER_OF_CASES;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NUMBER_OF_TASKS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.START_FY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.YEAR;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = FINANCIAL_YEAR_TABLE)
public class FinancialYear extends CustomAuditableEntity  {
	private static final long serialVersionUID = 1L;

	@Column(name = NAME)
	private String name;

	@Column(name = YEAR)
	private Long year;

	@Column(name = START_FY)
	private LocalDate startFY;

	@Column(name = END_FY)
	private LocalDate endFY;

	@Column(name = NUMBER_OF_TASKS)
	private Long numberOfTasks;

	@Column(name = NUMBER_OF_CASES)
	private Long numberOfCases;

	@Column(name = IS_PROCESSED)
	private Boolean isProcessed;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = FINANCIAL_YEAR_MAPPER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FinancialCaseInfo> financialCaseInfos;

	@ManyToOne
	@JoinColumn(name = FINANCIAL_DATA_DELETION_ID, foreignKey = @ForeignKey(name = FK_FINANCIAL_DATA_DELETION_FINANCIAL_YEAR))
	private FinancialDataDeletion financialDataDeletion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public LocalDate getStartFY() {
		return startFY;
	}

	public void setStartFY(LocalDate startFY) {
		this.startFY = startFY;
	}

	public LocalDate getEndFY() {
		return endFY;
	}

	public void setEndFY(LocalDate endFY) {
		this.endFY = endFY;
	}

	public Long getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(Long numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public Long getNumberOfCases() {
		return numberOfCases;
	}

	public void setNumberOfCases(Long numberOfCases) {
		this.numberOfCases = numberOfCases;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public List<FinancialCaseInfo> getFinancialCaseInfos() {
		return financialCaseInfos;
	}

	public void setFinancialCaseInfos(List<FinancialCaseInfo> financialCaseInfos) {
		this.financialCaseInfos = financialCaseInfos;
	}

	public FinancialDataDeletion getFinancialDataDeletion() {
		return financialDataDeletion;
	}

	public void setFinancialDataDeletion(FinancialDataDeletion financialDataDeletion) {
		this.financialDataDeletion = financialDataDeletion;
	}
}
