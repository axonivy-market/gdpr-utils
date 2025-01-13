package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.Constants.EMPLOYEE_NAME_PATTERN;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.APPLICATION_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.APPROVAL_HISTORY_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.BASE_SALARY_GROSS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CANDIDATE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CHANGE_DESCRIPTION;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.COMPANY_CAR;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DOCUMENT_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EFFECTIVE_DATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMAIL_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMPLOYEE_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMPLOYEE_FIRSTNAME;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMPLOYEE_LASTNAME;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FIRST_SIGNATURE_AGREEMENT_IDS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FIRST_SIGNATURE_SIGNED_DOC_IDS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_APPROVAL_HISTORY_APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_ATTACHMENT_DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_ATTACHMENT_INTERNALOFFER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_EMAIL_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_INTERNALOFFER_EMAIL_INTERNALOFFER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.INTERNAL_OFFER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.INTERNAL_OFFER_APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.INTERNAL_OFFER_DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.INTERNAL_OFFER_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.INTERNAL_OFFER_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.JOB_TITLE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PAYMENT_INTERVAL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PERSON_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.POSITION_CODE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PROCESS_CHAIN;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REPORTS_TO_POSITION;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REQUEST_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REQUIRED_TRANSITION_LETTER;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.SECOND_SIGNATURE_AGREEMENT_IDS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.SECOND_SIGNATURE_SIGNED_DOC_IDS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATUS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.TALENTLINK_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XXLARGE_STRING_LENGTH;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.axonivy.utils.gdprconnector.adobeesign.SecondSignature;
import com.axonivy.utils.gdprconnector.enums.InternalOfferState;
import com.axonivy.utils.gdprconnector.enums.PaymentInterval;
import com.axonivy.utils.gdprconnector.enums.Status;
import com.axonivy.utils.gdprconnector.enums.YesNoAnswer;
import com.axonivy.utils.gdprconnector.persistence.entities.ApprovalHistory;
import com.axonivy.utils.gdprconnector.persistence.entities.CustomAuditableEntity;
import com.axonivy.utils.gdprconnector.persistence.entities.Document;
import com.axonivy.utils.gdprconnector.persistence.entities.Email;
import com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory;
import com.axonivy.utils.gdprconnector.persistence.util.Constants;

@Entity
@Table(name = INTERNAL_OFFER)
public class InternalOffer extends CustomAuditableEntity implements SecondSignature{
	private static final long serialVersionUID = 1L;

	@Column(name = EMPLOYEE_FIRSTNAME, length = DEFAULT_LARGE_STRING_LENGTH)
	private String employeeFirstName;

	@Column(name = EMPLOYEE_LASTNAME, length = DEFAULT_LARGE_STRING_LENGTH)
	private String employeeLastName;

	@Transient
	private String employeeDisplayName;

	@Column(name = TALENTLINK_EMAIL, length = DEFAULT_LARGE_STRING_LENGTH)
	private String talentlinkEmail;
	
	@Column(name = APPLICATION_ID)
	private Long applicationId;

	@Column(name = CANDIDATE_ID)
	private Long candidateId;
	
	@Column(name = EFFECTIVE_DATE)
	private LocalDate effectiveDate;

	@Column(name = CHANGE_DESCRIPTION, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String changeDescription;

	@Column(name = POSITION_CODE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String positionCode;

	@Column(name = BASE_SALARY_GROSS, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String baseSalaryGross;

	@Enumerated(value = EnumType.STRING)
	@Column(name = PAYMENT_INTERVAL, length = DEFAULT_LARGE_STRING_LENGTH)
	private PaymentInterval paymentInterval;

	@Column(name = PERSON_ID, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String personId;

	@Column(name = EMPLOYEE_EMAIL, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String employeeEmail;

	@AttributeOverrides(value = {
			@AttributeOverride(name = Constants.EXTERNAL_CODE, column = @Column(name = TableAndColumnNameDirectory.LEGAL_ENTITY_CODE, length = DEFAULT_LARGE_STRING_LENGTH)),
			@AttributeOverride(name = Constants.NAME, column = @Column(name = TableAndColumnNameDirectory.LEGAL_ENTITY_NAME, length = DEFAULT_LARGE_STRING_LENGTH)) })
	private FoObject legalEntity;

	@Column(name = REPORTS_TO_POSITION, length = DEFAULT_LARGE_STRING_LENGTH)
	private String reportsToPosition;

	@Column(name = JOB_TITLE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String jobTitle;

	@Enumerated(value = EnumType.STRING)
	@Column(name = COMPANY_CAR, length = DEFAULT_LARGE_STRING_LENGTH)
	private YesNoAnswer companyCar;

	@Enumerated(value = EnumType.STRING)
	@Column(name = REQUIRED_TRANSITION_LETTER, length = DEFAULT_LARGE_STRING_LENGTH)
	private YesNoAnswer requiredTransitionLetter;

	@Enumerated(value = EnumType.STRING)
	@Column(name = STATE, length = DEFAULT_SMALL_STRING_LENGTH)
	private InternalOfferState state;

	@Column(name = PROCESS_CHAIN)
	private String processChain;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = INTERNAL_OFFER_DOCUMENT, joinColumns = {
			@JoinColumn(name = INTERNAL_OFFER_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_ATTACHMENT_INTERNALOFFER)) }, inverseJoinColumns = @JoinColumn(name = DOCUMENT_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_ATTACHMENT_DOCUMENT)))
	private List<Document> attachments = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = INTERNAL_OFFER_APPROVAL_HISTORY, joinColumns = {
			@JoinColumn(name = REQUEST_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_APPROVAL_HISTORY)) }, inverseJoinColumns = @JoinColumn(name = APPROVAL_HISTORY_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_APPROVAL_HISTORY_APPROVAL_HISTORY)))
	private List<ApprovalHistory> approvalHistories = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = INTERNAL_OFFER_EMAIL, joinColumns = {
			@JoinColumn(name = INTERNAL_OFFER_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_EMAIL_INTERNALOFFER)) }, inverseJoinColumns = @JoinColumn(name = EMAIL_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_INTERNALOFFER_EMAIL_EMAIL)))
	private List<Email> emails = new ArrayList<>();

	@Enumerated(value = EnumType.STRING)
	@Column(name = STATUS, length = DEFAULT_SMALL_STRING_LENGTH)
	private Status status;

	@Column(name = FIRST_SIGNATURE_AGREEMENT_IDS, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String firstSignatureAgreementIds;

	@Column(name = FIRST_SIGNATURE_SIGNED_DOC_IDS, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String firstSignatureSignedDocIds;

	@Column(name = SECOND_SIGNATURE_AGREEMENT_IDS, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String secondSignatureAgreementIds;

	@Column(name = SECOND_SIGNATURE_SIGNED_DOC_IDS, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String secondSignatureSignedDocIds;

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeDisplayName() {
		return String.format(EMPLOYEE_NAME_PATTERN, defaultString(employeeFirstName), defaultString(employeeLastName));
	}

	public String getTalentlinkEmail() {
		return talentlinkEmail;
	}

	public void setTalentlinkEmail(String talentlinkEmail) {
		this.talentlinkEmail = talentlinkEmail;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getBaseSalaryGross() {
		return baseSalaryGross;
	}

	public void setBaseSalaryGross(String baseSalaryGross) {
		this.baseSalaryGross = baseSalaryGross;
	}

	public PaymentInterval getPaymentInterval() {
		return paymentInterval;
	}

	public void setPaymentInterval(PaymentInterval paymentInterval) {
		this.paymentInterval = paymentInterval;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public FoObject getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(FoObject legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getReportsToPosition() {
		return reportsToPosition;
	}

	public void setReportsToPosition(String reportsToPosition) {
		this.reportsToPosition = reportsToPosition;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public YesNoAnswer getCompanyCar() {
		return companyCar;
	}

	public void setCompanyCar(YesNoAnswer companyCar) {
		this.companyCar = companyCar;
	}

	public YesNoAnswer getRequiredTransitionLetter() {
		return requiredTransitionLetter;
	}

	public void setRequiredTransitionLetter(YesNoAnswer requiredTransitionLetter) {
		this.requiredTransitionLetter = requiredTransitionLetter;
	}

	public InternalOfferState getState() {
		return state;
	}

	public void setState(InternalOfferState state) {
		this.state = state;
	}

	public String getProcessChain() {
		return processChain;
	}

	public void setProcessChain(String processChain) {
		this.processChain = processChain;
	}

	public List<Document> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Document> attachments) {
		this.attachments = attachments;
	}

	public List<ApprovalHistory> getApprovalHistories() {
		return approvalHistories;
	}

	public void setApprovalHistories(List<ApprovalHistory> approvalHistories) {
		this.approvalHistories = approvalHistories;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getFirstSignatureAgreementIds() {
		return firstSignatureAgreementIds;
	}

	public void setFirstSignatureAgreementIds(String firstSignatureAgreementIds) {
		this.firstSignatureAgreementIds = firstSignatureAgreementIds;
	}

	public String getFirstSignatureSignedDocIds() {
		return firstSignatureSignedDocIds;
	}

	public void setFirstSignatureSignedDocIds(String firstSignatureSignedDocIds) {
		this.firstSignatureSignedDocIds = firstSignatureSignedDocIds;
	}

	public String getSecondSignatureAgreementIds() {
		return secondSignatureAgreementIds;
	}

	public void setSecondSignatureAgreementIds(String secondSignatureAgreementIds) {
		this.secondSignatureAgreementIds = secondSignatureAgreementIds;
	}

	public String getSecondSignatureSignedDocIds() {
		return secondSignatureSignedDocIds;
	}

	public void setSecondSignatureSignedDocIds(String secondSignatureSignedDocIds) {
		this.secondSignatureSignedDocIds = secondSignatureSignedDocIds;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
}
