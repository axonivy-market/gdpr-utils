package com.axonivy.utils.gdprconnector.persistence.dao;

import com.axonivy.utils.gdprconnector.enums.CustomField;
import com.axonivy.utils.gdprconnector.persistence.entities.ImagineChangeAward;
import com.axonivy.utils.gdprconnector.persistence.entities.ImagineChangeAward_;
import com.axonivy.utils.persistence.dao.AuditableDAO;

import ch.ivyteam.ivy.workflow.ICase;

public class ImagineChangeAwardDAO extends AuditableDAO<ImagineChangeAward_, ImagineChangeAward> implements IHrBaseDAO {
	private static final ImagineChangeAwardDAO instance = new ImagineChangeAwardDAO();

	private ImagineChangeAwardDAO() {
	}

	public static ImagineChangeAwardDAO getInstance() {
		return instance;
	}

	@Override
	protected Class<ImagineChangeAward> getType() {
		return ImagineChangeAward.class;
	}

	public ImagineChangeAward getForCase(ICase wfCase) {
		ImagineChangeAward vs = findById(
				wfCase.customFields().stringField(CustomField.ENTITY_ID.getFieldName()).get().orElse(null));
		if (vs == null) {
			vs = new ImagineChangeAward();
		}
		return vs;
	}
}
