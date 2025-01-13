package com.axonivy.utils.gdprconnector.bo;

public class IPeopleAbstractEmployee {
	protected String userId;
	protected FoObject legalEntity;
	protected FoObject businessUnit;
	protected FoObject division;
	protected FoObject department;
	protected FoObject status;
	protected FoObject location;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FoObject getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(FoObject legalEntity) {
		this.legalEntity = legalEntity;
	}

	public FoObject getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(FoObject businessUnit) {
		this.businessUnit = businessUnit;
	}

	public FoObject getDivision() {
		return division;
	}

	public void setDivision(FoObject division) {
		this.division = division;
	}

	public FoObject getDepartment() {
		return department;
	}

	public void setDepartment(FoObject department) {
		this.department = department;
	}

	public FoObject getStatus() {
		return status;
	}

	public void setStatus(FoObject status) {
		this.status = status;
	}

	public FoObject getLocation() {
		return location;
	}

	public void setLocation(FoObject location) {
		this.location = location;
	}
}
