package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TableAndColumnNameDirectory.HR_TICKET_LD_GENERAL_QUERIES)
public class HRTicketLDGeneralQueries extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = TableAndColumnNameDirectory.COOR_PERSONAL_ID, length = DEFAULT_SMALL_STRING_LENGTH)
	private String coorPersonalId;

	@Column(name = TableAndColumnNameDirectory.ADDITIONAL_COMMENTS_TO_LD, length = DEFAULT_LARGE_STRING_LENGTH)
	private String additionalCommentsToLD;

	public String getCoorPersonalId() {
		return coorPersonalId;
	}

	public void setCoorPersonalId(String coorPersonalId) {
		this.coorPersonalId = coorPersonalId;
	}

	public String getAdditionalCommentsToLD() {
		return additionalCommentsToLD;
	}

	public void setAdditionalCommentsToLD(String additionalCommentsToLD) {
		this.additionalCommentsToLD = additionalCommentsToLD;
	}
}
