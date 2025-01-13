package com.axonivy.utils.gdprconnector.service;

import java.util.List;

import javax.transaction.TransactionRolledbackException;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYear;
import com.axonivy.utils.gdprconnector.persistence.entities.FinancialYearDAO;

import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;

public class FinancialYearService {
//	private static FinancialYearDAO dao = FinancialYearDAO.getInstance();
	private static FinancialYearService instance;
	private static int DEFAULT_SEARCH_LIMIT = 1000;

	private FinancialYearService() {
	}

	public static FinancialYearService getInstance() {
		if (instance == null) {
			instance = new FinancialYearService();
		}
		return instance;
	}
//
//	public FinancialYear save(FinancialYear financialYear) {
//		return dao.save(financialYear);
//	}
//
//	public List<FinancialYear> saveAll(List<FinancialYear> financialYears) {
//		try {
//			return dao.saveAll(financialYears);
//		} catch (TransactionRolledbackException e) {
//			Ivy.log().error("Cannot save all financial years", e);
//		}
//		return null;
//	}
//
//	public FinancialYear findByYear(Long year) {
//		var resutl = dao.findByYear(year);
//		return CollectionUtils.isEmpty(resutl) ? null : resutl.get(0);
//	}
//
//	public FinancialYear findById(String id) {
//		return dao.findById(id);
//	}
//
//	public List<FinancialYear> findAll() {
//		return dao.findAll();
//	}
//
//	public List<FinancialYear> findAllProcessedFinancialYears() {
//		return dao.findAllProcessedFinancialYears();
//	}
	
	public FinancialYear findById(String id) {
		FinancialYear financialYear = Ivy.repo().find(id, FinancialYear.class);
		if (financialYear != null) {
			return financialYear;
		}
		return null;
	}
	
	public FinancialYear save(FinancialYear financialYear) {
		Ivy.repo().save(financialYear);
		return financialYear;
	}
	
	public void delete(FinancialYear financialYear) {
		Ivy.repo().delete(financialYear);
	}	
	
	public void deleteById(String id) {
		Ivy.repo().deleteById(id);
	}
	
	public List<FinancialYear> findAll() {
		return Ivy.repo().search(FinancialYear.class).limit(DEFAULT_SEARCH_LIMIT).execute().getAll();
	}
	
	public List<FinancialYear> findAllProcessedFinancialYears() {
		return Ivy.repo().search(FinancialYear.class).booleanField("isProcessed").isTrue().execute().getAll();
	}
	
	public List<FinancialYear> saveAll(List<FinancialYear> financialYears) {
		for (FinancialYear year : financialYears) {
			save(year);
		}
		return findAll();
	}
	
	public List<FinancialYear> findByYear(Long financialYear) {
		return Ivy.repo().search(FinancialYear.class).numberField("year").isEqualTo(financialYear).execute().getAll();
	}
	
	public void deleteAll(List<FinancialYear> financialYears) {
		for (FinancialYear year : financialYears) {
			delete(year);
		}
	}
}
