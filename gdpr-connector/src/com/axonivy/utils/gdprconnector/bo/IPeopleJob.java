package com.axonivy.utils.gdprconnector.bo;

import java.time.LocalDate;

public class IPeopleJob extends IPeopleAbstractEmployee {
	private String jobTitle;
	private Double weeklyHours;
	private Double workingDaysPerWek;
	private FoObject contractType;
	private LocalDate contractEndDate;
	private LocalDate probationPeriodEndDate;
	private String manager;
	private FoObject payrollCostCentre;
	private IPeopleUser reportsToPosition;
	private IPeopleUser hrBusinessPartner;
	private IPeopleUser matrixManager;
	private FoObject serviceLine;
	private String capabilityUnit;
	private FoObject noticePeriod;
	private String positionId;
	private LocalDate secondmentEndDate;
	private FoObject employeeType;
	private FoObject employeeClass;
	private Double fte;
	private String variableCompensationType;
	private FoObject personnelArea;
	private FoObject personnelSubarea;
	private FoObject collectiveAgreement;
	private FoObject nonRicohLocation;
	private FoObject relationClause;
	private Boolean isCompetitionClauseActive;
	private FoObject legalGuardianshipForWorkScheduleNav;
	private LocalDate legalGuardianshipEndDate;
	private FoObject employmentType;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Double getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(Double weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	public Double getWorkingDaysPerWek() {
		return workingDaysPerWek;
	}

	public void setWorkingDaysPerWek(Double workingDaysPerWek) {
		this.workingDaysPerWek = workingDaysPerWek;
	}

	public FoObject getContractType() {
		return contractType;
	}

	public void setContractType(FoObject contractType) {
		this.contractType = contractType;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public LocalDate getProbationPeriodEndDate() {
		return probationPeriodEndDate;
	}

	public void setProbationPeriodEndDate(LocalDate probationPeriodEndDate) {
		this.probationPeriodEndDate = probationPeriodEndDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public FoObject getPayrollCostCentre() {
		return payrollCostCentre;
	}

	public void setPayrollCostCentre(FoObject payrollCostCentre) {
		this.payrollCostCentre = payrollCostCentre;
	}

	public IPeopleUser getReportsToPosition() {
		return reportsToPosition;
	}

	public void setReportsToPosition(IPeopleUser reportsToPosition) {
		this.reportsToPosition = reportsToPosition;
	}

	public IPeopleUser getHrBusinessPartner() {
		return hrBusinessPartner;
	}

	public void setHrBusinessPartner(IPeopleUser hrBusinessPartner) {
		this.hrBusinessPartner = hrBusinessPartner;
	}

	public IPeopleUser getMatrixManager() {
		return matrixManager;
	}

	public void setMatrixManager(IPeopleUser matrixManager) {
		this.matrixManager = matrixManager;
	}

	public FoObject getServiceLine() {
		return serviceLine;
	}

	public void setServiceLine(FoObject serviceLine) {
		this.serviceLine = serviceLine;
	}

	public String getCapabilityUnit() {
		return capabilityUnit;
	}

	public void setCapabilityUnit(String capabilityUnit) {
		this.capabilityUnit = capabilityUnit;
	}

	public FoObject getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(FoObject noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public LocalDate getSecondmentEndDate() {
		return secondmentEndDate;
	}

	public void setSecondmentEndDate(LocalDate secondmentEndDate) {
		this.secondmentEndDate = secondmentEndDate;
	}

	public FoObject getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(FoObject employeeType) {
		this.employeeType = employeeType;
	}

	public FoObject getEmployeeClass() {
		return employeeClass;
	}

	public void setEmployeeClass(FoObject employeeClass) {
		this.employeeClass = employeeClass;
	}

	public Double getFte() {
		return fte;
	}

	public void setFte(Double fte) {
		this.fte = fte;
	}

	public String getVariableCompensationType() {
		return variableCompensationType;
	}

	public void setVariableCompensationType(String variableCompensationType) {
		this.variableCompensationType = variableCompensationType;
	}

	public FoObject getPersonnelArea() {
		return personnelArea;
	}

	public void setPersonnelArea(FoObject personnelArea) {
		this.personnelArea = personnelArea;
	}

	public FoObject getPersonnelSubarea() {
		return personnelSubarea;
	}

	public void setPersonnelSubarea(FoObject personnelSubarea) {
		this.personnelSubarea = personnelSubarea;
	}

	public FoObject getCollectiveAgreement() {
		return collectiveAgreement;
	}

	public void setCollectiveAgreement(FoObject collectiveAgreement) {
		this.collectiveAgreement = collectiveAgreement;
	}

	public FoObject getNonRicohLocation() {
		return nonRicohLocation;
	}

	public void setNonRicohLocation(FoObject nonRicohLocation) {
		this.nonRicohLocation = nonRicohLocation;
	}

	public FoObject getRelationClause() {
		return relationClause;
	}

	public void setRelationClause(FoObject relationClause) {
		this.relationClause = relationClause;
	}

	public Boolean getIsCompetitionClauseActive() {
		return isCompetitionClauseActive;
	}

	public void setIsCompetitionClauseActive(Boolean isCompetitionClauseActive) {
		this.isCompetitionClauseActive = isCompetitionClauseActive;
	}	
	
	public FoObject getLegalGuardianshipForWorkScheduleNav() {
		return legalGuardianshipForWorkScheduleNav;
	}

	public void setLegalGuardianshipForWorkScheduleNav(FoObject legalGuardianshipForWorkScheduleNav) {
		this.legalGuardianshipForWorkScheduleNav = legalGuardianshipForWorkScheduleNav;
	}

	public LocalDate getLegalGuardianshipEndDate() {
		return legalGuardianshipEndDate;
	}

	public void setLegalGuardianshipEndDate(LocalDate legalGuardianshipEndDate) {
		this.legalGuardianshipEndDate = legalGuardianshipEndDate;
	}

	public FoObject getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(FoObject employmentType) {
		this.employmentType = employmentType;
	}
}
