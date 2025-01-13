package com.axonivy.utils.gdprconnector.bo;

import java.time.OffsetDateTime;

public class IPeoplePerson {
	private String firstName;
	private String lastName;
	private String secondLastName;
	private String driverLicenseNumber;
	private OffsetDateTime driverLicenseExpDate;
	private String email;
	private String nationalId;
	private String legalEntityId;
	private String iPeopleId;
	private OffsetDateTime endDate;
	private String city;
	private String zipCode;
	private String address1;

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

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public OffsetDateTime getDriverLicenseExpDate() {
		return driverLicenseExpDate;
	}

	public void setDriverLicenseExpDate(OffsetDateTime driverLicenseExpDate) {
		this.driverLicenseExpDate = driverLicenseExpDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getiPeopleId() {
		return iPeopleId;
	}

	public void setiPeopleId(String iPeopleId) {
		this.iPeopleId = iPeopleId;
	}
	
	public OffsetDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
}
