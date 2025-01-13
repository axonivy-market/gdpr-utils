package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.APPROVAL_HISTORY_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.ASSOCIATED_TICKET;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CASE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.COMMENT_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CONTACT_PERSON;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DELEGATED_TO;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DESCRIPTION;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DOCUMENT_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMAIL_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_APPROVAL_HISTORY_APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_APPROVAL_HISTORY_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_COMMENT_COMMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_COMMENT_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_DOCUMENT_DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_DOCUMENT_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_EMAIL_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_EMAIL_HR_TICKET_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_HR_TICKET_LD_ACTIVITY_REQUEST;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_HR_TICKET_LD_GENERAL_QUERIES;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_HR_TICKET_SYSTEM_QUERIES;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FK_HR_TICKET_REQUEST_HR_TICKET_WORKFORCE_INSIGHTS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.FORWARD_TO_MAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_LD_ACTIVITY_REQUEST_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_LD_GENERAL_QUERIES_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_REQUEST_COMMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_REQUEST_DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_REQUEST_EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_REQUEST_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_SYTEM_QUERIES_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HR_TICKET_WORKFORCE_INSIGHTS_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.RAISER_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REQUEST_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATUS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.TICKET_TYPE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.TITLE;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_MEDIUM_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.axonivy.utils.gdprconnector.enums.HRTicketRequestState;
import com.axonivy.utils.gdprconnector.enums.HRTicketType;
import com.axonivy.utils.gdprconnector.enums.Status;

public class HRTicketRequest extends CustomAuditableEntity {
	private static final long serialVersionUID = -787700480090851562L;

	@Column(name = CASE_ID, length = DEFAULT_XLARGE_STRING_LENGTH)
	private Long caseId;

	@Column(name = TITLE, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String title;

	@Column(name = DESCRIPTION)
	private String description;

	@Column(name = RAISER_ID, length = DEFAULT_LARGE_STRING_LENGTH)
	private String raiserId;

	@Enumerated(EnumType.STRING)
	@Column(name = TICKET_TYPE, length = DEFAULT_LARGE_STRING_LENGTH)
	private HRTicketType ticketType;

	@Column(name = FORWARD_TO_MAIL, length = DEFAULT_LARGE_STRING_LENGTH)
	private String forwardToMail;

	@Enumerated(value = EnumType.STRING)
	@Column(name = STATE, length = DEFAULT_SMALL_STRING_LENGTH)
	private HRTicketRequestState state;
	
	@Column(name = DELEGATED_TO, length = DEFAULT_MEDIUM_STRING_LENGTH)
	private String delegatedTo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = HR_TICKET_APPROVAL_HISTORY, joinColumns = {
			@JoinColumn(name = REQUEST_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_APPROVAL_HISTORY_REQUEST)) }, inverseJoinColumns = @JoinColumn(name = APPROVAL_HISTORY_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_APPROVAL_HISTORY_APPROVAL_HISTORY)))
	private List<ApprovalHistory> approvalHistories = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = HR_TICKET_REQUEST_DOCUMENT, joinColumns = {
			@JoinColumn(name = REQUEST_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_DOCUMENT_REQUEST)) }, inverseJoinColumns = @JoinColumn(name = DOCUMENT_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_DOCUMENT_DOCUMENT)))
	private List<Document> documents = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = HR_TICKET_REQUEST_COMMENT, joinColumns = {
			@JoinColumn(name = REQUEST_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_COMMENT_REQUEST)) }, inverseJoinColumns = @JoinColumn(name = COMMENT_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_COMMENT_COMMENT)))
	private List<Comment> comments = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = HR_TICKET_WORKFORCE_INSIGHTS_ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_HR_TICKET_WORKFORCE_INSIGHTS))
	private HRTicketWorkforceInsights hRTicketWorkforceInsights;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = HR_TICKET_SYTEM_QUERIES_ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_HR_TICKET_SYSTEM_QUERIES))
	private HRTicketSystemQueries systemQueries;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = HR_TICKET_LD_GENERAL_QUERIES_ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_HR_TICKET_LD_GENERAL_QUERIES))
	private HRTicketLDGeneralQueries ldGeneralQueries;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = HR_TICKET_LD_ACTIVITY_REQUEST_ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_HR_TICKET_LD_ACTIVITY_REQUEST))
	private HRTicketLDActivityRequest ldActivityRequest;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = HR_TICKET_REQUEST_EMAIL, joinColumns = {
			@JoinColumn(name = HR_TICKET_REQUEST_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_EMAIL_HR_TICKET_REQUEST)) }, inverseJoinColumns = @JoinColumn(name = EMAIL_ID, referencedColumnName = ID, foreignKey = @ForeignKey(name = FK_HR_TICKET_REQUEST_EMAIL_EMAIL)))
	private List<Email> emails = new ArrayList<>();

	@Enumerated(value = EnumType.STRING)
	@Column(name = STATUS, length = DEFAULT_SMALL_STRING_LENGTH)
	private Status status;

	@Column(name = ASSOCIATED_TICKET, length = DEFAULT_MEDIUM_STRING_LENGTH)
	private String associatedTicket;

	@Column(name = CONTACT_PERSON, length = DEFAULT_MEDIUM_STRING_LENGTH)
	private String contactPerson;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRaiserId() {
		return raiserId;
	}

	public void setRaiserId(String raiserId) {
		this.raiserId = raiserId;
	}

	public HRTicketType getTicketType() {
		return ticketType;
	}

	public String getForwardToMail() {
		return forwardToMail;
	}

	public void setForwardToMail(String forwardToMail) {
		this.forwardToMail = forwardToMail;
	}

	public void setTicketType(HRTicketType ticketType) {
		this.ticketType = ticketType;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public HRTicketRequestState getState() {
		return state;
	}

	public void setState(HRTicketRequestState state) {
		this.state = state;
	}

	public List<ApprovalHistory> getApprovalHistories() {
		return approvalHistories;
	}

	public void setApprovalHistories(List<ApprovalHistory> approvalHistories) {
		this.approvalHistories = approvalHistories;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HRTicketWorkforceInsights gethRTicketWorkforceInsights() {
		return hRTicketWorkforceInsights;
	}

	public void sethRTicketWorkforceInsights(HRTicketWorkforceInsights hRTicketWorkforceInsights) {
		this.hRTicketWorkforceInsights = hRTicketWorkforceInsights;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public HRTicketSystemQueries getSystemQueries() {
		return systemQueries;
	}

	public void setSystemQueries(HRTicketSystemQueries systemQueries) {
		this.systemQueries = systemQueries;
	}

	public HRTicketLDGeneralQueries getLdGeneralQueries() {
		return ldGeneralQueries;
	}

	public void setLdGeneralQueries(HRTicketLDGeneralQueries ldGeneralQueries) {
		this.ldGeneralQueries = ldGeneralQueries;
	}

	public HRTicketLDActivityRequest getLdActivityRequest() {
		return ldActivityRequest;
	}

	public void setLdActivityRequest(HRTicketLDActivityRequest ldActivityRequest) {
		this.ldActivityRequest = ldActivityRequest;
	}

	public String getAssociatedTicket() {
		return associatedTicket;
	}

	public void setAssociatedTicket(String associatedTicket) {
		this.associatedTicket = associatedTicket;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getDelegatedTo() {
		return delegatedTo;
	}

	public void setDelegatedTo(String delegatedTo) {
		this.delegatedTo = delegatedTo;
	}
}
