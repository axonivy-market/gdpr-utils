package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CASE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CONTENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.EMAIL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.MAIL_TYPE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.RECIPIENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.SUBJECT;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.axonivy.utils.gdprconnector.enums.MailType;

@Entity
@Table(name = EMAIL)
public class Email extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = RECIPIENT, length = DEFAULT_LARGE_STRING_LENGTH)
	private String recipient;

	@Column(name = SUBJECT, length = DEFAULT_LARGE_STRING_LENGTH)
	private String subject;

	@Column(name = DATE)
	private LocalDateTime date;

	@Lob
	@Column(name = CONTENT)
	private String content;
	
	@Column(name = CASE_ID, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String caseId;
	
	@Column(name = MAIL_TYPE)
	@Enumerated(EnumType.STRING)
	private MailType mailType;

	public Email() {
	}

	public Email(String recipient, String subject, LocalDateTime date, String content, String caseId,
			MailType mailType) {
		this.setRecipient(recipient);
		this.setSubject(subject);
		this.setDate(date);
		this.setContent(content);
		this.setCaseId(caseId);
		this.setMailType(mailType);
	}

	public MailType getMailType() {
		return mailType;
	}

	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}
	
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
