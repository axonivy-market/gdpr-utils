package com.axonivy.utils.gdprconnector.service;

import static java.util.Objects.nonNull;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataEmpJob;
import com.axonivy.connector.successfactors.connector.rest.SFODataPicklistOption;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.bo.IPeopleJob;
import com.axonivy.utils.gdprconnector.utils.IPeopleUserUtils;

public class IPeopleJobFactory {
	public static IPeopleJob createJob(SFODataEmpJob empJob) {
		IPeopleJob job = new IPeopleJob();
		job.setJobTitle(empJob.getJobTitle());
		job.setBusinessUnit(FoObjectFactory.create(empJob.getBusinessUnitNav()));
		job.setDivision(FoObjectFactory.create(empJob.getDivisionNav()));
		job.setDepartment(FoObjectFactory.create(empJob.getDepartmentNav()));
		job.setWeeklyHours(empJob.getStandardHours());
		job.setWorkingDaysPerWek(empJob.getWorkingDaysPerWeek());
		job.setStatus(FoObjectFactory.create(empJob.getEmplStatusNav()));
		job.setPayrollCostCentre(FoObjectFactory.create(empJob.getCostCenterNav()));
		job.setServiceLine(FoObjectFactory.create(empJob.getCustomString93Nav()));
		job.setCapabilityUnit(empJob.getCustomString94());
		job.setPersonnelArea(FoObjectFactory.create(empJob.getCustomString5Nav()));
		job.setPersonnelSubarea(FoObjectFactory.create(empJob.getCustomString6Nav()));
		job.setCollectiveAgreement(FoObjectFactory.create(empJob.getCustomString42Nav()));
		job.setLocation(FoObjectFactory.create(empJob.getLocationNav()));
		job.setPositionId(empJob.getPosition());
		job.setSecondmentEndDate(
				Optional.ofNullable(empJob.getCustomDate2()).map(OffsetDateTime::toLocalDate).orElse(null));
		job.setFte(empJob.getFte());
		job.setUserId(empJob.getUserId());
		job.setIsCompetitionClauseActive(empJob.isIsCompetitionClauseActive());
		Optional.ofNullable(empJob.getCustomString37Nav()).map(SFODataPicklistOption::getPicklistLabels)
				.map(picklistLabels -> picklistLabels.get(0)).map(picklistLabel -> picklistLabel.getLabel())
				.ifPresent(label -> job.setVariableCompensationType(label));
		if (nonNull(empJob.getCompanyNav())) {
			job.setLegalEntity(FoObjectFactory.create(empJob.getCompanyNav().getCustLegalEntityToPYCtrlArea()));
		}
		if (nonNull(empJob.getContractEndDate())) {
			job.setContractEndDate(empJob.getContractEndDate().toLocalDate());
		}
		if (nonNull(empJob.getProbationPeriodEndDate())) {
			job.setProbationPeriodEndDate(empJob.getProbationPeriodEndDate().toLocalDate());
		}
		if (nonNull(empJob.getManagerEmploymentNav())) {
			job.setManager(empJob.getManagerEmploymentNav().getPersonIdExternal());
			job.setReportsToPosition(IPeopleUserUtils.convertToUser(empJob.getManagerEmploymentNav().getUserNav()));
		}
		if (empJob.getManagerUserNav() != null) {
			job.setHrBusinessPartner(IPeopleUserUtils.convertToUser(empJob.getManagerUserNav().getHr()));
			CollectionUtils.emptyIfNull(empJob.getManagerUserNav().getMatrixManager())
					.stream().findFirst().ifPresent(data -> {
						job.setMatrixManager(IPeopleUserUtils.convertToUser(data));
					});
		}
		if (nonNull(empJob.getEmploymentTypeNav())) {
			job.setEmploymentType(FoObjectFactory.create(empJob.getEmploymentTypeNav()));
		}

		var foData = new FoObject();
		// Contract type
		if (empJob.getContractTypeNav() != null) {
			job.setContractType(FoObjectFactory.create(empJob.getContractTypeNav()));
		} else {
			foData.setExternalCode(empJob.getContractType());
			foData.setName(empJob.getContractType());
			job.setContractType(foData);
		}
		// Notice period
		if (empJob.getNoticePeriodNav() != null) {
			job.setNoticePeriod(FoObjectFactory.create(empJob.getNoticePeriodNav()));
		} else {
			foData.setExternalCode(empJob.getNoticePeriod());
			foData.setName(empJob.getNoticePeriod());
			job.setNoticePeriod(foData);
		}
		// Employee type
		if (empJob.getEmployeeTypeNav() != null) {
			job.setEmployeeType(FoObjectFactory.create(empJob.getEmployeeTypeNav()));
		} else {
			foData.setExternalCode(empJob.getEmployeeType());
			foData.setName(empJob.getEmployeeType());
			job.setEmployeeType(foData);
		}
		// Employee class
		if (empJob.getEmployeeClassNav() != null) {
			job.setEmployeeClass(FoObjectFactory.create(empJob.getEmployeeClassNav()));
		} else {
			foData.setExternalCode(empJob.getEmployeeClass());
			foData.setName(empJob.getEmployeeClass());
			job.setEmployeeClass(foData);
		}

		// Non-Ricoh location
		if (empJob.getCustomString30Nav() != null) {
			job.setNonRicohLocation(FoObjectFactory.create(empJob.getCustomString30Nav()));
		}

		// Relation clause
		if (empJob.getCustomString32Nav() != null) {
			job.setRelationClause(FoObjectFactory.create(empJob.getCustomString32Nav()));
		}
		
		// Legal guardianship for work schedule
		if (empJob.getCustomString38Nav() != null) {
			job.setLegalGuardianshipForWorkScheduleNav(FoObjectFactory.create(empJob.getCustomString38Nav()));
		}

		// Legal guardianship end date
		if (empJob.getCustomDate11() != null) {
			job.setLegalGuardianshipEndDate(empJob.getCustomDate11().toLocalDate());
		}

		return job;
	}
}
