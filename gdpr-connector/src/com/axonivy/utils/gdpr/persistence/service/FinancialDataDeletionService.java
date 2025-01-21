package com.axonivy.utils.gdpr.persistence.service;

import java.util.List;

import com.axonivy.utils.gdpr.persistence.entities.FinancialDataDeletion;

import ch.ivyteam.ivy.environment.Ivy;


public class FinancialDataDeletionService {
	private static FinancialDataDeletionService instance;
	private static int DEFAULT_SEARCH_LIMIT = 1000;

	private FinancialDataDeletionService() { }

	public static FinancialDataDeletionService getInstance() {
		if (instance == null) {
			instance = new FinancialDataDeletionService();
		}
		return instance;
	}

	public String save(FinancialDataDeletion financialDataDeletion) {
		return Ivy.repo().save(financialDataDeletion).getId();
	}

	public List<FinancialDataDeletion> findByCaseId(long caseId) {
		return Ivy.repo().search(FinancialDataDeletion.class).numberField("caseId").isEqualTo(caseId).execute().getAll();
	}
	
	public FinancialDataDeletion findById(String financialDataDeletionId) {
		return Ivy.repo().find(financialDataDeletionId, FinancialDataDeletion.class);
	}

	public List<FinancialDataDeletion> findAllProcessedFinancialYears() {
		var query = Ivy.repo().search(FinancialDataDeletion.class).booleanField("financialYears.isProcessed").isTrue();
		long totalCount = query.execute().count();
		return query.limit(0, (int) totalCount).execute().getAll();
	}
}
