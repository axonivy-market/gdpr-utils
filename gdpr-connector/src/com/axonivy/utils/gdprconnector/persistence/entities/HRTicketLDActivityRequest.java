package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XXLARGE_STRING_LENGTH;

import java.time.LocalDate;

import javax.persistence.Column;

public class HRTicketLDActivityRequest extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = TableAndColumnNameDirectory.TARGET_GO_LIVE)
	private LocalDate targetGoLive;

	@Column(name = TableAndColumnNameDirectory.COOR_PERSONAL_ID, length = DEFAULT_SMALL_STRING_LENGTH)
	private String coorPersonalId;

	@Column(name = TableAndColumnNameDirectory.LANGUAGES, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String languages;

	@Column(name = TableAndColumnNameDirectory.SIZE_OF_TARGET_AUDIENCE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String sizeOfTargetAudience;

	@Column(name = TableAndColumnNameDirectory.LEGAL_ENTITIES_IN_SCOPE, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String legalEntitiesInScope;

	@Column(name = TableAndColumnNameDirectory.LINE_OF_BUSINESS, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String lineOfBusiness;

	@Column(name = TableAndColumnNameDirectory.SPONSOR_USER_IDS, length = DEFAULT_SMALL_STRING_LENGTH)
	private String sponsorUserIds;

	@Column(name = TableAndColumnNameDirectory.BUSSINESS_REPRESENTATIVE_USER_ID, length = DEFAULT_SMALL_STRING_LENGTH)
	private String businessRepresentativeUserId;

	@Column(name = TableAndColumnNameDirectory.OBJECTIVE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String objective;

	@Column(name = TableAndColumnNameDirectory.KEY_BENEFITS, length = DEFAULT_LARGE_STRING_LENGTH)
	private String keyBenefits;

	@Column(name = TableAndColumnNameDirectory.ADDITIONAL_COMMENTS_TO_LD, length = DEFAULT_LARGE_STRING_LENGTH)
	private String additionalCommentsToLD;

	public LocalDate getTargetGoLive() {
		return targetGoLive;
	}

	public void setTargetGoLive(LocalDate targetGoLive) {
		this.targetGoLive = targetGoLive;
	}

	public String getCoorPersonalId() {
		return coorPersonalId;
	}

	public void setCoorPersonalId(String coorPersonalId) {
		this.coorPersonalId = coorPersonalId;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getSizeOfTargetAudience() {
		return sizeOfTargetAudience;
	}

	public void setSizeOfTargetAudience(String sizeOfTargetAudience) {
		this.sizeOfTargetAudience = sizeOfTargetAudience;
	}

	public String getLegalEntitiesInScope() {
		return legalEntitiesInScope;
	}

	public void setLegalEntitiesInScope(String legalEntitiesInScope) {
		this.legalEntitiesInScope = legalEntitiesInScope;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getSponsorUserIds() {
		return sponsorUserIds;
	}

	public void setSponsorUserIds(String sponsorUserIds) {
		this.sponsorUserIds = sponsorUserIds;
	}

	public String getBusinessRepresentativeUserId() {
		return businessRepresentativeUserId;
	}

	public void setBusinessRepresentativeUserId(String businessRepresentativeUserId) {
		this.businessRepresentativeUserId = businessRepresentativeUserId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getKeyBenefits() {
		return keyBenefits;
	}

	public void setKeyBenefits(String keyBenefits) {
		this.keyBenefits = keyBenefits;
	}

	public String getAdditionalCommentsToLD() {
		return additionalCommentsToLD;
	}

	public void setAdditionalCommentsToLD(String additionalCommentsToLD) {
		this.additionalCommentsToLD = additionalCommentsToLD;
	}
}
