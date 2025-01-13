package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.IMAGINE_CHANGE_AWARD;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.LEGAL_ENTITY_CODE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.LEGAL_ENTITY_NAME;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NOMINATOR;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NOMINEE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REPORTING_TO;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.RICOH_WAY_VALUE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.THANK_YOU_MESSAGE;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.EXTERNAL_CODE;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.NAME;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;
import com.axonivy.utils.gdprconnector.enums.RicohWayValue;

@Entity
@Table(name = IMAGINE_CHANGE_AWARD)
public class ImagineChangeAward extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = NOMINATOR, length = DEFAULT_LARGE_STRING_LENGTH)
	private String nominator;

	@Column(name = NOMINEE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String nominee;

	@Column(name = REPORTING_TO, length = DEFAULT_LARGE_STRING_LENGTH)
	private String reportingTo;

	@AttributeOverrides(value = {
			@AttributeOverride(name = EXTERNAL_CODE, column = @Column(name = LEGAL_ENTITY_CODE, length = DEFAULT_LARGE_STRING_LENGTH)),
			@AttributeOverride(name = NAME, column = @Column(name = LEGAL_ENTITY_NAME, length = DEFAULT_LARGE_STRING_LENGTH)) })
	private FoObject legalEntity;

	@Enumerated(EnumType.STRING)
	@Column(name = RICOH_WAY_VALUE, length = DEFAULT_LARGE_STRING_LENGTH)
	private RicohWayValue ricohWayValue;

	@Column(name = THANK_YOU_MESSAGE, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String thankYouMessage;

	@Transient
	private IPeopleUser iPeopleNominator;
	@Transient
	private IPeopleUser iPeopleNominee;
	@Transient
	private IPeopleUser iPeopleReportingTo;

	public String getNominator() {
		return nominator;
	}

	public void setNominator(String nominator) {
		this.nominator = nominator;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}


	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public FoObject getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(FoObject legalEntity) {
		this.legalEntity = legalEntity;
	}

	public RicohWayValue getRicohWayValue() {
		return ricohWayValue;
	}

	public void setRicohWayValue(RicohWayValue ricohWayValue) {
		this.ricohWayValue = ricohWayValue;
	}

	public String getThankYouMessage() {
		return thankYouMessage;
	}

	public void setThankYouMessage(String thankYouMessage) {
		this.thankYouMessage = thankYouMessage;
	}

	public IPeopleUser getiPeopleNominator() {
		return iPeopleNominator;
	}

	public void setiPeopleNominator(IPeopleUser iPeopleNominator) {
		this.iPeopleNominator = iPeopleNominator;
	}

	public IPeopleUser getiPeopleNominee() {
		return iPeopleNominee;
	}

	public void setiPeopleNominee(IPeopleUser iPeopleNominee) {
		this.iPeopleNominee = iPeopleNominee;
	}

	public IPeopleUser getiPeopleReportingTo() {
		return iPeopleReportingTo;
	}

	public void setiPeopleReportingTo(IPeopleUser iPeopleReportingTo) {
		this.iPeopleReportingTo = iPeopleReportingTo;
	}
}
