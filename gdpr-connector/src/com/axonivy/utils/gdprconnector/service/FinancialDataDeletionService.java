package com.axonivy.utils.gdprconnector.service;

import java.util.List;

import javax.persistence.criteria.Path;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import com.axonivy.utils.gdprconnector.persistence.dao.FinancialDataDeletionDAO;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialDataDeletion;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import com.axonivy.utils.persistence.beans.GenericIdEntity_;
import com.axonivy.utils.persistence.dao.CriteriaQueryContext;

import ch.ivyteam.ivy.environment.Ivy;


public class FinancialDataDeletionService {
	private static FinancialDataDeletionService instance;
	private static int DEFAULT_SEARCH_LIMIT = 1000;

	private FinancialDataDeletionService() {
	}

	public static FinancialDataDeletionService getInstance() {
		if (instance == null) {
			instance = new FinancialDataDeletionService();
		}
		return instance;
	}
	
	public FinancialDataDeletion save(FinancialDataDeletion financialDataDeletion) {
		Ivy.repo().save(financialDataDeletion);
		return financialDataDeletion;
	}
	
	public List<FinancialDataDeletion> findByCaseId(long caseId) {
		return Ivy.repo().search(FinancialDataDeletion.class).numberField("caseId").isEqualTo(caseId).execute().getAll();
	}
	
	public FinancialDataDeletion findById(String financialDataDeletionId) {
		FinancialDataDeletion financialDataDeletion = Ivy.repo().find(financialDataDeletionId, FinancialDataDeletion.class);
		if (financialDataDeletion != null) {
			return financialDataDeletion;
		}
		return null;
	}
	
	public FinancialDataDeletion findByIdWithRelatedData(String financialDataDeletionId) {
		FinancialDataDeletion financialDataDeletion = null;
		try (CriteriaQueryContext<FinancialDataDeletion> query = initializeQuery();) {
			Path<String> idPath = query.r.get(GenericIdEntity_.id);
			query.whereEq(idPath, financialDataDeletionId);
			List<FinancialDataDeletion> results = findByCriteria(query);
			if (CollectionUtils.isNotEmpty(results)) {
				financialDataDeletion = results.get(0);
				Hibernate.initialize(financialDataDeletion.getFinancialYears());
			}
			return financialDataDeletion;
		}
		return null;
	}
}
