package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.ADVISE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_WORKFORCE_INSIGHTS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.LEVEL_OF_IMPORTANCE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.LEVEL_OF_URGENCY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PURPOSE_OF_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PURPOSE_USED;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.axonivy.utils.gdprconnector.enums.HRTicketLevel;

@Entity
@Table(name = HR_TICKET_WORKFORCE_INSIGHTS)
public class HRTicketWorkforceInsights extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = PURPOSE_OF_REQUEST, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String purposeOfRequest;

	@Enumerated(EnumType.STRING)
	@Column(name = LEVEL_OF_URGENCY, length = DEFAULT_LARGE_STRING_LENGTH)
	private HRTicketLevel levelOfUrgency;

	@Enumerated(EnumType.STRING)
	@Column(name = LEVEL_OF_IMPORTANCE, length = DEFAULT_LARGE_STRING_LENGTH)
	private HRTicketLevel levelOfImportance;

	@Column(name = ADVISE, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String advise;

	@Column(name = PURPOSE_USED, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String purposeUsed;

	public String getPurposeOfRequest() {
		return purposeOfRequest;
	}

	public void setPurposeOfRequest(String purposeOfRequest) {
		this.purposeOfRequest = purposeOfRequest;
	}

	public HRTicketLevel getLevelOfUrgency() {
		return levelOfUrgency;
	}

	public void setLevelOfUrgency(HRTicketLevel levelOfUrgency) {
		this.levelOfUrgency = levelOfUrgency;
	}

	public HRTicketLevel getLevelOfImportance() {
		return levelOfImportance;
	}

	public void setLevelOfImportance(HRTicketLevel levelOfImportance) {
		this.levelOfImportance = levelOfImportance;
	}

	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	public String getPurposeUsed() {
		return purposeUsed;
	}

	public void setPurposeUsed(String purposeUsed) {
		this.purposeUsed = purposeUsed;
	}
}
