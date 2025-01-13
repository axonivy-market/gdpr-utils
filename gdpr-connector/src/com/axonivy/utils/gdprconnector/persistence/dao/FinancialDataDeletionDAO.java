package com.axonivy.utils.gdprconnector.persistence.dao;

import java.util.List;

import javax.persistence.criteria.Path;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import com.axonivy.utils.gdprconnector.persistence.entities.FinancialDataDeletion;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialDataDeletion_;
import com.axonivy.utils.persistence.beans.GenericIdEntity_;
import com.axonivy.utils.persistence.dao.AuditableDAO;
import com.axonivy.utils.persistence.dao.CriteriaQueryContext;

import ch.ivyteam.ivy.environment.Ivy;

public class FinancialDataDeletionDAO  extends AuditableDAO<FinancialDataDeletion_, FinancialDataDeletion> implements IHrBaseDAO {
	@Override
	protected Class<FinancialDataDeletion> getType() {
		return FinancialDataDeletion.class;
	}

	public List<FinancialDataDeletion> findByCaseId(Long caseId) {
		try (CriteriaQueryContext<FinancialDataDeletion> query = initializeQuery();) {
			query.whereEq(FinancialDataDeletion_.caseId, caseId);
			return findByCriteria(query);
		} catch (Exception e) {
			Ivy.log().error("Cannot query FinancialDataDeletion", e);
		}
		return List.of();
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
	}
	
	public FinancialDataDeletion findByIdWithAllRelatedData(String financialDataDeletionId) {
		FinancialDataDeletion financialDataDeletion = null;
		try (CriteriaQueryContext<FinancialDataDeletion> query = initializeQuery();) {
			Path<String> idPath = query.r.get(GenericIdEntity_.id);
			query.whereEq(idPath, financialDataDeletionId);
			List<FinancialDataDeletion> results = findByCriteria(query);
			if (CollectionUtils.isNotEmpty(results)) {
				financialDataDeletion = results.get(0);
				Hibernate.initialize(financialDataDeletion.getFinancialYears());
				financialDataDeletion.getFinancialYears().forEach(year-> Hibernate.initialize(year.getFinancialCaseInfos()));
			}
			return financialDataDeletion;
		}
	}
}
