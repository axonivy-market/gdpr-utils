package com.axonivy.utils.gdprconnector.persistence.dao;

import java.util.List;

import javax.persistence.criteria.Path;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import com.axonivy.utils.persistence.beans.GenericIdEntity_;
import com.axonivy.utils.persistence.dao.AuditableDAO;
import com.axonivy.utils.persistence.dao.CriteriaQueryContext;
import com.ricoh.hr.core.persistence.dao.IHrBaseDAO;
import com.ricoh.hr.core.persistence.dao.InternalOfferDAO;
import com.ricoh.hr.core.persistence.entities.InternalOffer;
import com.ricoh.hr.core.persistence.entities.InternalOffer_;

public class InternalOfferDAO extends AuditableDAO<InternalOffer_, InternalOffer> implements IHrBaseDAO {
	private static final InternalOfferDAO instance = new InternalOfferDAO();

	public static InternalOfferDAO getInstance() {
		return instance;
	}

	@Override
	protected Class<InternalOffer> getType() {
		return InternalOffer.class;
	}

	public InternalOffer findByIdWithLoadingRelatedData(String id) {
		InternalOffer internalOffer = null;
		try (CriteriaQueryContext<InternalOffer> query = initializeQuery();) {
			Path<String> idPath = query.r.get(GenericIdEntity_.id);
			query.whereEq(idPath, id);
			List<InternalOffer> results = findByCriteria(query);
			if (CollectionUtils.isNotEmpty(results)) {
				internalOffer = results.get(0);
				Hibernate.initialize(internalOffer.getAttachments());
				Hibernate.initialize(internalOffer.getApprovalHistories());
				Hibernate.initialize(internalOffer.getEmails());
			}
		}
		return internalOffer;
	}
}
