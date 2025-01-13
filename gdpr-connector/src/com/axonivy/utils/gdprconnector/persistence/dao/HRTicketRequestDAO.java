package com.axonivy.utils.gdprconnector.persistence.dao;

import java.util.List;

import javax.persistence.criteria.Path;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import com.axonivy.utils.gdprconnector.persistence.entities.HRTicketRequest;
import com.axonivy.utils.gdprconnector.persistence.entities.HRTicketRequest_;
import com.axonivy.utils.persistence.beans.GenericIdEntity_;
import com.axonivy.utils.persistence.dao.AuditableDAO;
import com.axonivy.utils.persistence.dao.CriteriaQueryContext;

public class HRTicketRequestDAO extends AuditableDAO<HRTicketRequest_, HRTicketRequest> implements IHrBaseDAO {
	private static final HRTicketRequestDAO instance = new HRTicketRequestDAO();

	private HRTicketRequestDAO() {
	}

	public static HRTicketRequestDAO getInstance() {
		return instance;
	}

	@Override
	protected Class<HRTicketRequest> getType() {
		return HRTicketRequest.class;
	}
	
	public HRTicketRequest findByCaseId(Long caseId) {
		try (CriteriaQueryContext<HRTicketRequest> query = initializeQuery();) {
			query.whereEq(HRTicketRequest_.caseId, caseId);
			List<HRTicketRequest> results = findByCriteria(query);
			return CollectionUtils.isNotEmpty(results) ? results.get(0) : null;
		}
	}
	
	public HRTicketRequest findByIdWithLoadingRelatedData(String id) {
		HRTicketRequest request = null;
		try (CriteriaQueryContext<HRTicketRequest> query = initializeQuery();) {
			Path<String> idPath = query.r.get(GenericIdEntity_.id);
			query.whereEq(idPath, id);
			List<HRTicketRequest> results = findByCriteria(query);
			if (CollectionUtils.isNotEmpty(results)) {
				request = results.get(0);
				Hibernate.initialize(request.getApprovalHistories());
				Hibernate.initialize(request.getDocuments());
				Hibernate.initialize(request.getComments());
				Hibernate.initialize(request.getEmails());
			}
			return request;
		}
	}
}
