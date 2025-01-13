package com.axonivy.utils.gdprconnector.enums;

import java.util.stream.Collectors;

import com.axonivy.utils.gdprconnector.utils.IPeopleUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;

public enum Roles {
	DEFAULT("Everybody"), ADMINISTRATOR("Administrator"), MANAGER("Manager"), EMPLOYEE("Employee"), BOARD_MEMBER("Board Member"),
	PEOPLE_SERVICE_SPECIALIST("People Service Specialist"), ONBOARDING_CONSULTANT("Onboarding Consultant"),
	CASE_OWNER_POSITION_MAINTENANCE_CHECK("Case Owner Position Maintenance Check"),
	LOCAL_HR_RBS_POLAND("Local HR RBS Poland"), LOCAL_HR_RICOH_POLAND("Local HR Ricoh Poland"),
	LOCAL_HR_ITS_SOC_POLAND("Local HR ITS SOC Poland"), LOCAL_HR_RICOH_UK("Local HR Ricoh UK"),
	LOCAL_HR_RICOH_EUROPE("Local HR Ricoh Europe"), LOCAL_HR_RICOH_IRELAND("Local HR Ricoh Ireland"),
	LOCAL_HR_RICOH_SWEDEN("Local HR Ricoh Sweden"), LOCAL_HR_RICOH_NORWAY("Local HR Ricoh Norway"),
	LOCAL_HR_RICOH_DENMARK("Local HR Ricoh Denmark"), LOCAL_HR_RICOH_SOUTH_AFRICA("Local HR Ricoh South Africa"),
	LOCAL_HR_RIBV("Local HR RIBV"), LOCAL_HR("Local HR"), LOCAL_HR_RICOH_FINLAND("Local HR Ricoh Finland"),
	LOCAL_HR_RBSGDS("Local HR RBSGDS"), LOCAL_HR_RICOH_FRANCE("Local HR Ricoh France"),
	LOCAL_HR_RICOH_SPAIN("Local HR Ricoh Spain"), LOCAL_HR_RICOH_SPAIN_IPM("Local HR Ricoh Spain IPM"),
	LOCAL_HR_RICOH_SPAIN_ITS("Local HR Ricoh Spain ITS"), LOCAL_HR_RICOH_EUROPE_NETHERLANDS_BV("Local HR Ricoh Europe Netherlands BV"),
	LOCAL_HR_RICOH_EUROPE_SCM("Local Ricoh Europe SCM"), LOCAL_HR_RICOH_NEDERLAND("Local HR Ricoh Nederland"), 
	LOCAL_HR_RICOH_PORTUGAL("Local HR Ricoh Portugal"),
	LOCAL_HR_RICOH_PORTUGAL_TOTALSTOR("Local HR Ricoh Portugal Totalstor"),
	LOCAL_HR_RICOH_BELGIUM("Local HR Ricoh Belgium"), LOCAL_HR_RICOH_LUXEMBOURG("Local HR Ricoh Luxembourg"),
	LOCAL_HR_RICOH_ITALIA_SRL("Local HR Ricoh Italia Srl"), LOCAL_HR_RICOH_TURKEY("Local HR Ricoh Turkey"),
	LOCAL_HR_RICOH_CZECH_REPUBLIC("Local HR Ricoh Czech Republic"), LOCAL_HR_RICOH_SLOVAKIA("Local HR Ricoh Slovakia"),
	LOCAL_HR_RICOH_HUNGARY_KFT("Local HR Ricoh Hungary Kft"),
	LOCAL_HR_RICOH_AUSTRIA_GMBH("Local HR Ricoh Austria GmbH"),
	LOCAL_HR_RICOH_DEUTSCHLAND_GMBH("Local HR Ricoh Deutschland GmbH"),
	LOCAL_HR_RICOH_INTERNATIONAL_BV("Local HR Ricoh International B.V. (Düsseldorf)"),
	LOCAL_HR_RICOH_SCHWEIZ_AG("Local HR Ricoh Schweiz AG"),
	MAINTAIN_ORGANIZATION_STRUCTURE_CREATOR("Maintain Organization Structure Creator"), HR_IS_ANALYST("HR IS Analyst"),
	TALENT_ACQUISITION("Talent Acquisition"),
	//// Local Talent Acquisition 
	TALENT_ACQUISITION_RDK("Talent Acquisition RDK"),
	TALENT_ACQUISITION_REU_REPLC("Talent Acquisition REU/REPLC"),
	TALENT_ACQUISITION_RFI("Talent Acquisition RFI"),
	TALENT_ACQUISITION_RIE("Talent Acquisition RIE"),
	TALENT_ACQUISITION_RNO("Talent Acquisition RNO"),
	TALENT_ACQUISITION_RSE("Talent Acquisition RSE"),
	TALENT_ACQUISITION_RUK("Talent Acquisition RUK"),
	TALENT_ACQUISITION_RZA("Talent Acquisition RZA"),
	TALENT_ACQUISITION_RIBV("Talent Acquisition RIBV"),
	TALENT_ACQUISITION_RPO("Talent Acquisition RPO"),
	TALENT_ACQUISITION_RBSPL_RBS("Talent Acquisition RBSPL/RBS"),
	TALENT_ACQUISITION_RFR("Talent Acquisition RFR"),
	TALENT_ACQUISITION_RES("Talent Acquisition RES"),
	TALENT_ACQUISITION_RESIPM("Talent Acquisition RESIPM"),
	TALENT_ACQUISITION_RESITS("Talent Acquisition RESITS"),
	TALENT_ACQUISITION_RENBV("Talent Acquisition RENBV"),
	TALENT_ACQUISITION_RESCM("Talent Acquisition RESCM"),
	TALENT_ACQUISITION_RNL("Talent Acquisition RNL"),
	TALENT_ACQUISITION_RPT("Talent Acquisition RPT"),
	TALENT_ACQUISITION_RPTTS("Talent Acquisition RPTTS"),
	TALENT_ACQUISITION_RBE("Talent Acquisition RBE"),
	TALENT_ACQUISITION_RLU("Talent Acquisition RLU"),
	TALENT_ACQUISITION_RIT("Talent Acquisition RIT"),
	TALENT_ACQUISITION_RTR("Talent Acquisition RTR"),
	TALENT_ACQUISITION_RCZ_RSK("Talent Acquisition RCZ/RSK"),
	TALENT_ACQUISITION_RHU("Talent Acquisition RHU"),
	TALENT_ACQUISITION_RAT("Talent Acquisition RAT"),
	TALENT_ACQUISITION_RDE("Talent Acquisition RDE"),
	TALENT_ACQUISITION_RIG("Talent Acquisition RIG"),
	TALENT_ACQUISITION_RCH("Talent Acquisition RCH"),
	
	WORKFORCE_INSIGHTS("Workforce Insights"), L_AND_D("L&D"), PRE_HIRE("Pre-Hire"), ALL_OPCOS_ADMIN("AllOpCosAdmin"),
	HR_BUSINESS_PARTNER("HR Business Partner"), WORK_COUNCIL("Work Council"),
	RIBV_SHARED_SERVICES("RIBV shared Services"),
	KNOWLEDGE_BASE_CONTENT_CREATOR("Knowledge Base Content Creator"),
	//Pso Admin
	PSO_ADMIN("PSO Admin"),
	//Local Payroll Roles
	PAYROLL_RBS_RPO("Payroll RBS RPO"),
	PAYROLL_RICOH_POLAND("Payroll RPO"),
	PAYROLL_RICOH_UK("Payroll RUK"),
	PAYROLL_RICOH_EUROPE("Payroll REU"),
	PAYROLL_RICOH_IRELAND("Payroll RIE"),
	PAYROLL_RICOH_SWEDEN("Payroll RSE"),
	PAYROLL_RICOH_NORWAY("Payroll RNO"),
	PAYROLL_RICOH_DENMARK("Payroll RDK"),
	PAYROLL_RICOH_SOUTH_AFRICA("Payroll RZA"),
	PAYROLL_RIBV("Payroll RIBV"),
	PAYROLL_RICOH_FINLAND("Payroll RFI"),
	PAYROLL_RICOH_NEDERLAND("Payroll RNL"),
	PAYROLL_RICOH_EUROPE_SCM("Payroll RESCM"),
	PAYROLL_RICOH_EUROPE_NETHERLANDS_BV("Payroll RENBV"),
	WORK_COUNCIL_RDE("Works Council for RDE"), 
	WORK_COUNCIL_RIG("Works Council for RIG"),
	RFR_PAY_PLAN("RFRPayplan"),
	
	// PSO - Case owner check
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RUK("PSO-CaseOwnerMaintenanceCheck-RUK"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RBS("PSO-CaseOwnerMaintenanceCheck-RBS"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RPL("PSO-CaseOwnerMaintenanceCheck-RPL"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_REU("PSO-CaseOwnerMaintenanceCheck-REU"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RIE("PSO-CaseOwnerMaintenanceCheck-RIE"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RSE("PSO-CaseOwnerMaintenanceCheck-RSE"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RNO("PSO-CaseOwnerMaintenanceCheck-RNO"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RDK("PSO-CaseOwnerMaintenanceCheck-RDK"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RZA("PSO-CaseOwnerMaintenanceCheck-RZA"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RIBV("PSO-CaseOwnerMaintenanceCheck-RIBV"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RFI("PSO-CaseOwnerMaintenanceCheck-RFI"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RFR("PSO-CaseOwnerMaintenanceCheck-RFR"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RES("PSO-CaseOwnerMaintenanceCheck-RES"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RESIPM("PSO-CaseOwnerMaintenanceCheck-RESIPM"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RESITS("PSO-CaseOwnerMaintenanceCheck-RESITS"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RENBV("PSO-CaseOwnerMaintenanceCheck-RENBV"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RESCM("PSO-CaseOwnerMaintenanceCheck-RESCM"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RNL("PSO-CaseOwnerMaintenanceCheck-RNL"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RPT("PSO-CaseOwnerMaintenanceCheck-RPT"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RPTTS("PSO-CaseOwnerMaintenanceCheck-RPTTS"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RBE("PSO-CaseOwnerMaintenanceCheck-RBE"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RLU("PSO-CaseOwnerMaintenanceCheck-RLU"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RIT("PSO-CaseOwnerMaintenanceCheck-RIT"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RTR("PSO-CaseOwnerMaintenanceCheck-RTR"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RCZ("PSO-CaseOwnerMaintenanceCheck-RCZ"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RSK("PSO-CaseOwnerMaintenanceCheck-RSK"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RHU("PSO-CaseOwnerMaintenanceCheck-RHU"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RAT("PSO-CaseOwnerMaintenanceCheck-RAT"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RDE("PSO-CaseOwnerMaintenanceCheck-RDE"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RIG("PSO-CaseOwnerMaintenanceCheck-RIG"),
	PSO_CASE_OWNER_MAINTENANCE_CHECK_RCH("PSO-CaseOwnerMaintenanceCheck-RCH"),

	// Workforce Insights - Case owner check
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RUK("WFI-CaseOwnerMaintenanceCheck-RUK"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RBS("WFI-CaseOwnerMaintenanceCheck-RBS"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RPL("WFI-CaseOwnerMaintenanceCheck-RPL"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_REU("WFI-CaseOwnerMaintenanceCheck-REU"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RIE("WFI-CaseOwnerMaintenanceCheck-RIE"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RSE("WFI-CaseOwnerMaintenanceCheck-RSE"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RNO("WFI-CaseOwnerMaintenanceCheck-RNO"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RDK("WFI-CaseOwnerMaintenanceCheck-RDK"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RZA("WFI-CaseOwnerMaintenanceCheck-RZA"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RIBV("WFI-CaseOwnerMaintenanceCheck-RIBV"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RFI("WFI-CaseOwnerMaintenanceCheck-RFI"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RFR("WFI-CaseOwnerMaintenanceCheck-RFR"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RES("WFI-CaseOwnerMaintenanceCheck-RES"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RESIPM("WFI-CaseOwnerMaintenanceCheck-RESIPM"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RESITS("WFI-CaseOwnerMaintenanceCheck-RESITS"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RENBV("WFI-CaseOwnerMaintenanceCheck-RENBV"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RESCM("WFI-CaseOwnerMaintenanceCheck-RESCM"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RNL("WFI-CaseOwnerMaintenanceCheck-RNL"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RPT("WFI-CaseOwnerMaintenanceCheck-RPT"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RPTTS("WFI-CaseOwnerMaintenanceCheck-RPTTS"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RBE("WFI-CaseOwnerMaintenanceCheck-RBE"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RLU("WFI-CaseOwnerMaintenanceCheck-RLU"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RIT("WFI-CaseOwnerMaintenanceCheck-RIT"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RTR("WFI-CaseOwnerMaintenanceCheck-RTR"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RCZ("WFI-CaseOwnerMaintenanceCheck-RCZ"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RSK("WFI-CaseOwnerMaintenanceCheck-RSK"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RHU("WFI-CaseOwnerMaintenanceCheck-RHU"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RAT("WFI-CaseOwnerMaintenanceCheck-RAT"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RDE("WFI-CaseOwnerMaintenanceCheck-RDE"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RIG("WFI-CaseOwnerMaintenanceCheck-RIG"),
	WFI_CASE_OWNER_MAINTENANCE_CHECK_RCH("WFI-CaseOwnerMaintenanceCheck-RCH"),

	// Learning & Development - Case owner check
	LD_CASE_OWNER_MAINTENANCE_CHECK_RUK("L&D-CaseOwnerMaintenanceCheck-RUK"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RBS("L&D-CaseOwnerMaintenanceCheck-RBS"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RPL("L&D-CaseOwnerMaintenanceCheck-RPL"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_REU("L&D-CaseOwnerMaintenanceCheck-REU"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RIE("L&D-CaseOwnerMaintenanceCheck-RIE"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RSE("L&D-CaseOwnerMaintenanceCheck-RSE"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RNO("L&D-CaseOwnerMaintenanceCheck-RNO"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RDK("L&D-CaseOwnerMaintenanceCheck-RDK"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RZA("L&D-CaseOwnerMaintenanceCheck-RZA"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RIBV("L&D-CaseOwnerMaintenanceCheck-RIBV"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RFI("L&D-CaseOwnerMaintenanceCheck-RFI"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RFR("L&D-CaseOwnerMaintenanceCheck-RFR"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RES("L&D-CaseOwnerMaintenanceCheck-RES"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RESIPM("L&D-CaseOwnerMaintenanceCheck-RESIPM"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RESITS("L&D-CaseOwnerMaintenanceCheck-RESITS"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RENBV("L&D-CaseOwnerMaintenanceCheck-RENBV"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RESCM("L&D-CaseOwnerMaintenanceCheck-RESCM"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RNL("L&D-CaseOwnerMaintenanceCheck-RNL"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RPT("L&D-CaseOwnerMaintenanceCheck-RPT"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RPTTS("L&D-CaseOwnerMaintenanceCheck-RPTTS"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RBE("L&D-CaseOwnerMaintenanceCheck-RBE"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RLU("L&D-CaseOwnerMaintenanceCheck-RLU"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RIT("L&D-CaseOwnerMaintenanceCheck-RIT"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RTR("L&D-CaseOwnerMaintenanceCheck-RTR"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RCZ("L&D-CaseOwnerMaintenanceCheck-RCZ"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RSK("L&D-CaseOwnerMaintenanceCheck-RSK"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RHU("L&D-CaseOwnerMaintenanceCheck-RHU"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RAT("L&D-CaseOwnerMaintenanceCheck-RAT"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RDE("L&D-CaseOwnerMaintenanceCheck-RDE"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RIG("L&D-CaseOwnerMaintenanceCheck-RIG"),
	LD_CASE_OWNER_MAINTENANCE_CHECK_RCH("L&D-CaseOwnerMaintenanceCheck-RCH"),

	// Talent Acquisition - Case owner check
	TA_CASE_OWNER_MAINTENANCE_CHECK_RUK("TA-CaseOwnerMaintenanceCheck-RUK"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RBS("TA-CaseOwnerMaintenanceCheck-RBS"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RPL("TA-CaseOwnerMaintenanceCheck-RPL"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_REU("TA-CaseOwnerMaintenanceCheck-REU"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RIE("TA-CaseOwnerMaintenanceCheck-RIE"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RSE("TA-CaseOwnerMaintenanceCheck-RSE"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RNO("TA-CaseOwnerMaintenanceCheck-RNO"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RDK("TA-CaseOwnerMaintenanceCheck-RDK"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RZA("TA-CaseOwnerMaintenanceCheck-RZA"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RIBV("TA-CaseOwnerMaintenanceCheck-RIBV"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RFI("TA-CaseOwnerMaintenanceCheck-RFI"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RFR("TA-CaseOwnerMaintenanceCheck-RFR"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RES("TA-CaseOwnerMaintenanceCheck-RES"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RESIPM("TA-CaseOwnerMaintenanceCheck-RESIPM"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RESITS("TA-CaseOwnerMaintenanceCheck-RESITS"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RENBV("TA-CaseOwnerMaintenanceCheck-RENBV"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RESCM("TA-CaseOwnerMaintenanceCheck-RESCM"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RNL("TA-CaseOwnerMaintenanceCheck-RNL"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RPT("TA-CaseOwnerMaintenanceCheck-RPT"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RPTTS("TA-CaseOwnerMaintenanceCheck-RPTTS"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RBE("TA-CaseOwnerMaintenanceCheck-RBE"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RLU("TA-CaseOwnerMaintenanceCheck-RLU"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RIT("TA-CaseOwnerMaintenanceCheck-RIT"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RTR("TA-CaseOwnerMaintenanceCheck-RTR"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RCZ("TA-CaseOwnerMaintenanceCheck-RCZ"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RSK("TA-CaseOwnerMaintenanceCheck-RSK"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RHU("TA-CaseOwnerMaintenanceCheck-RHU"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RAT("TA-CaseOwnerMaintenanceCheck-RAT"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RDE("TA-CaseOwnerMaintenanceCheck-RDE"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RIG("TA-CaseOwnerMaintenanceCheck-RIG"),
	TA_CASE_OWNER_MAINTENANCE_CHECK_RCH("TA-CaseOwnerMaintenanceCheck-RCH");

	private String ivyRoleName;

	private Roles() {
		this.ivyRoleName = name();
	}

	private Roles(String ivyRoleName) {
		this.ivyRoleName = ivyRoleName;
	}

	/**
	 * Get the Ivy Role name.
	 *
	 * @return
	 */
	public String getIvyRoleName() {
		return ivyRoleName;
	}

	public IRole getIvyRole() {
		return Ivy.wf().getSecurityContext().roles().find(ivyRoleName);
	}

	public String getAllEmailsJoined(String separator) {
		return getIvyRole().users().allPaged().stream().map(IPeopleUtils::getEmailAddressOfIvyUser)
				.collect(Collectors.joining(separator));
	}

	public boolean getCanUserActAsThisRole() {
		final IRole role = getIvyRole();

		if (role != null) {
			return Ivy.session().hasRole(role, true);
		} else {
			Ivy.log().warn("Role not found, will not show features connected to this role:" + ivyRoleName);
			return false;
		}
	}
}
