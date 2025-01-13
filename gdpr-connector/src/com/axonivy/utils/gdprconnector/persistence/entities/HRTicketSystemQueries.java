package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.axonivy.utils.gdprconnector.enums.Impact;

@Entity
@Table(name = TableAndColumnNameDirectory.HR_TICKET_SYSTEM_QUERIES)
public class HRTicketSystemQueries extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = TableAndColumnNameDirectory.COOR_PERSONAL_ID, length = DEFAULT_SMALL_STRING_LENGTH)
	private String coorPersonalId;

	@Enumerated(value = EnumType.STRING)
	@Column(name = TableAndColumnNameDirectory.IMPACT, length = DEFAULT_LARGE_STRING_LENGTH)
	private Impact impact;

	@Column(name = TableAndColumnNameDirectory.BROWSER, length = DEFAULT_LARGE_STRING_LENGTH)
	private String browsers; 

	@Column(name = TableAndColumnNameDirectory.ADDITIONAL_COMMENTS_TO_LD, length = DEFAULT_LARGE_STRING_LENGTH)
	private String additionalCommentsToLD;

	public String getCoorPersonalId() {
		return coorPersonalId;
	}

	public void setCoorPersonalId(String coorPersonalId) {
		this.coorPersonalId = coorPersonalId;
	}

	public Impact getImpact() {
		return impact;
	}

	public void setImpact(Impact impact) {
		this.impact = impact;
	}

	public String getBrowsers() {
		return browsers;
	}

	public void setBrowsers(String browsers) {
		this.browsers = browsers;
	}

	public String getAdditionalCommentsToLD() {
		return additionalCommentsToLD;
	}

	public void setAdditionalCommentsToLD(String additionalCommentsToLD) {
		this.additionalCommentsToLD = additionalCommentsToLD;
	}
}
