package com.axonivy.utils.gdprconnector.bo;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.StringUtils;

public class IPeopleUser {
	private String userId;
	private String personIdExternal;
	private String firstName;
	private String lastName;
	private String email;
	private String legalEntityId;
	private String legalEntityDisplayName;
	private OffsetDateTime endDate;
	private String status;
	private String displayName;
	private String defaultFullName;
	private String username;
	private String nationalId;
	private String salutation;
	private IPeopleUser matrixManager;
	private OffsetDateTime dateOfBirth;

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public OffsetDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(OffsetDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the value of the "custom05" property
	 */
	public String getLegalEntityDisplayName() {
		return legalEntityDisplayName;
	}

	public void setLegalEntityDisplayName(String legalEntityDisplayName) {
		this.legalEntityDisplayName = legalEntityDisplayName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPersonIdExternal() {
		return personIdExternal;
	}

	public void setPersonIdExternal(String personIdExternal) {
		this.personIdExternal = personIdExternal;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDefaultFullName() {
		return defaultFullName;
	}

	public void setDefaultFullName(String defaultFullName) {
		this.defaultFullName = defaultFullName;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public IPeopleUser getMatrixManager() {
		return matrixManager;
	}

	public void setMatrixManager(IPeopleUser matrixManager) {
		this.matrixManager = matrixManager;
	}

	public String getIdentityNumber() {
		return StringUtils.isBlank(personIdExternal) ? userId : personIdExternal;
	}
}
