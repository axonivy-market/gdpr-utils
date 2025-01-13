package com.axonivy.utils.gdprconnector.persistence.entities;

import java.util.List;

import com.axonivy.utils.gdprconnector.persistence.dao.IHrBaseDAO;
import com.axonivy.utils.persistence.dao.AuditableDAO;
import com.axonivy.utils.persistence.dao.CriteriaQueryContext;

import ch.ivyteam.ivy.environment.Ivy;

public class FinancialYearDAO extends AuditableDAO<FinancialYear_, FinancialYear> implements IHrBaseDAO{
	private static final FinancialYearDAO instance = new FinancialYearDAO();

	@Override
	protected Class<FinancialYear> getType() {
		return FinancialYear.class;
	}

	private FinancialYearDAO() {
	}

	public static FinancialYearDAO getInstance() {
		return instance;
	}

	public List<FinancialYear> findByYear(Long financialYear) {
		try (CriteriaQueryContext<FinancialYear> query = initializeQuery();) {
			query.whereEq(FinancialYear_.year, financialYear);
			return findByCriteria(query);
		} catch (Exception e) {
			Ivy.log().error("Cannot query FinancialYear", e);
		}
		return List.of();
	}

	public List<FinancialYear> findAllProcessedFinancialYears() {
		try (CriteriaQueryContext<FinancialYear> query = initializeQuery();) {
			query.whereEq(FinancialYear_.isProcessed, true);
			return findByCriteria(query);
		} catch (Exception e) {
			Ivy.log().error("Cannot query FinancialYear", e);
		}
		return List.of();
	}
}
