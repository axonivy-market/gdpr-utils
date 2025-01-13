package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.ACCESS_LEVEL;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CONTENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.CREATOR;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DOCUMENT_TYPE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DOCUWARE_ID;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.IS_DOCUWARE_DOCUMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.NAME;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PATH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XSMALL_STRING_LENGTH;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.axonivy.utils.gdprconnector.enums.AccessLevel;
import com.axonivy.utils.gdprconnector.enums.DocumentType;

@Entity
@Table(name = DOCUMENT)
public class Document  extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = CREATOR, length = DEFAULT_LARGE_STRING_LENGTH, nullable = false)
	@NotNull
	private String creator;

	@Column(name = PATH, length = DEFAULT_LARGE_STRING_LENGTH)
	private String path;

	@Column(name = DATE, nullable = false)
	@NotNull
	private LocalDate date;
	
	@Column(name = IS_DOCUWARE_DOCUMENT)
	private Boolean isDocuwareDocument;
	
	@Column(name = DOCUWARE_ID, nullable = true)
	private String docuwareId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = DOCUMENT_TYPE, length = DEFAULT_SMALL_STRING_LENGTH)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = ACCESS_LEVEL, length = DEFAULT_XSMALL_STRING_LENGTH)
	private AccessLevel accessLevel;
	
	@Column(name = NAME, length = DEFAULT_LARGE_STRING_LENGTH)
	private String name;

	@Lob
	@Column(name = CONTENT)
	private byte[] content;

	public Document() {
	}

	public Document(Document document) {
		this.id = document.id;
		this.creator = document.creator;
		this.date = document.date;
		this.name = document.name;
		this.content = document.content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDocuwareId() {
		return docuwareId;
	}

	public void setDocuwareId(String docuwareId) {
		this.docuwareId = docuwareId;
	}

	public Boolean getIsDocuwareDocument() {
		return isDocuwareDocument;
	}

	public void setIsDocuwareDocument(Boolean isDocuwareDocument) {
		this.isDocuwareDocument = isDocuwareDocument;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
}
