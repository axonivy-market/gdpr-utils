package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_DATA_DELETION;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CASE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FINANCIAL_DATA_DELETION_MAPPER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.LEGAL_ENTITIES_IN_SCOPE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = FINANCIAL_DATA_DELETION)
public class FinancialDataDeletion extends CustomAuditableEntity  {
	private static final long serialVersionUID = 1L;

	@Column(name = CASE_ID)
	private Long caseId;

	@Column(name = LEGAL_ENTITIES_IN_SCOPE)
	private String legalEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = FINANCIAL_DATA_DELETION_MAPPER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FinancialYear> financialYears;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getLegalEntities() {
		return legalEntities;
	}

	public void setLegalEntities(String legalEntities) {
		this.legalEntities = legalEntities;
	}

	public List<FinancialYear> getFinancialYears() {
		return financialYears;
	}

	public void setFinancialYears(List<FinancialYear> financialYears) {
		this.financialYears = financialYears;
	}
}
