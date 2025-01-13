package com.axonivy.utils.gdprconnector.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.bo.FoObject;

public enum LegalEntityEnum {
	RICOH_POLAND("4600", "RPO", Roles.LOCAL_HR_RICOH_POLAND, RequiredDocumentsInfo.RICOH_POLAND.getCmsName(),
			LegalEntityNewHire.RBS_POLAND,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RPL,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RPL, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RPL,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RPL, List.of(new Locale("pl")), Roles.TALENT_ACQUISITION_RPO),
	RICOH_UK("0801", "RUK", Roles.LOCAL_HR_RICOH_UK, RequiredDocumentsInfo.RICOH_UK.getCmsName(),
			LegalEntityNewHire.RICOH_UK, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RUK,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RUK, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RUK,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RUK, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RUK),
	RICOH_EUROPE("0802", "REU", Roles.LOCAL_HR_RICOH_EUROPE, RequiredDocumentsInfo.NOT_APPLICABLE.getCmsName(),
			LegalEntityNewHire.RICOH_EUROPE, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_REU,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_REU, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_REU,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_REU, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_REU_REPLC),
	RICOH_IRELAND("1100", "RIE", Roles.LOCAL_HR_RICOH_IRELAND, RequiredDocumentsInfo.RICOH_IRELAND.getCmsName(),
			LegalEntityNewHire.RICOH_IRELAND,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RIE, Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RIE,
			Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RIE, Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RIE, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RIE),
	RICOH_SWEDEN("2300", "RSE", Roles.LOCAL_HR_RICOH_SWEDEN, RequiredDocumentsInfo.RICOH_SWEDEN.getCmsName(),
			LegalEntityNewHire.RICOH_SWEDEN, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RSE,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RSE, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RSE,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RSE, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RSE),
	RICOH_NORWAY("2000", "RNO", Roles.LOCAL_HR_RICOH_NORWAY, RequiredDocumentsInfo.RICOH_NORWAY.getCmsName(),
			LegalEntityNewHire.RICOH_NORWAY, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RNO,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RNO, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RNO,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RNO, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RNO),
	RICOH_DENMARK("0900", "RDK", Roles.LOCAL_HR_RICOH_DENMARK, RequiredDocumentsInfo.NOT_APPLICABLE.getCmsName(),
			LegalEntityNewHire.RICOH_DENMARK,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RDK, Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RDK,
			Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RDK, Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RDK, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RDK),
	RICOH_FINLAND("4400", "RFI", Roles.LOCAL_HR_RICOH_FINLAND, RequiredDocumentsInfo.NOT_APPLICABLE.getCmsName(),
			LegalEntityNewHire.RICOH_FINLAND,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RFI, Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RFI,
			Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RFI, Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RFI, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RFI),
	RICOH_SOUTH_AFRICA("1600", "RZA", Roles.LOCAL_HR_RICOH_SOUTH_AFRICA,
			RequiredDocumentsInfo.RICOH_SOUTH_AFRICA.getCmsName(),
			LegalEntityNewHire.RICOH_SOUTH_AFRICA, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RZA,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RZA, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RZA,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RZA, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RZA),
	RIBV("0501", "RIBV", Roles.LOCAL_HR_RIBV, RequiredDocumentsInfo.RIBV.getCmsName(),
			LegalEntityNewHire.RIBV, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RIBV,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RIBV, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RIBV,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RIBV, List.of(new Locale("nl"), Locale.ENGLISH), Roles.TALENT_ACQUISITION_RIBV),
	RBSGDS("4601", "RBSGDS", Roles.LOCAL_HR_RBS_POLAND, RequiredDocumentsInfo.RBS_POLAND.getCmsName(),
			LegalEntityNewHire.RBS_POLAND, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RBS,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RBS, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RBS,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RBS, List.of(Locale.ENGLISH), Roles.TALENT_ACQUISITION_RBSPL_RBS),
	RICOH_FRANCE("0600", "RFR", Roles.LOCAL_HR_RICOH_FRANCE, "", LegalEntityNewHire.DEFAULT,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RFR, Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RFR,
			Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RFR, Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RFR, List.of(Locale.FRENCH), Roles.TALENT_ACQUISITION_RFR),
	RICOH_SPAIN("0400", "RES", Roles.LOCAL_HR_RICOH_SPAIN, "", LegalEntityNewHire.DEFAULT,
			Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RES, Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RES,
			Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RES, Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RES, List.of(new Locale("es")), Roles.TALENT_ACQUISITION_RES),
	RICOH_SPAIN_IPM("0403", "RESIPM", Roles.LOCAL_HR_RICOH_SPAIN_IPM, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RESIPM,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RESIPM, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RESIPM,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RESIPM, List.of(new Locale("es")), Roles.TALENT_ACQUISITION_RESIPM),
	RICOH_SPAIN_ITS("0401", "RESITS", Roles.LOCAL_HR_RICOH_SPAIN_ITS, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RESITS,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RESITS, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RESITS,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RESITS, List.of(new Locale("es")), Roles.TALENT_ACQUISITION_RESITS),
	RICOH_EUROPE_NETHERLANDS_BV("0500", "RENBV", Roles.LOCAL_HR_RICOH_EUROPE_NETHERLANDS_BV, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RENBV,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RENBV, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RENBV,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RENBV, List.of(new Locale("nl"), Locale.ENGLISH), Roles.TALENT_ACQUISITION_RENBV),
	RICOH_EUROPE_SCM("0503", "RESCM", Roles.LOCAL_HR_RICOH_EUROPE_SCM, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RESCM,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RESCM, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RESCM,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RESCM, List.of(new Locale("nl"), Locale.ENGLISH), Roles.TALENT_ACQUISITION_RESCM),
	RICOH_NEDERLAND("0502", "RNL", Roles.LOCAL_HR_RICOH_NEDERLAND, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RNL,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RNL, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RNL,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RNL, List.of(new Locale("nl")), Roles.TALENT_ACQUISITION_RNL),
	RICOH_PORTUGAL("1900", "RPT", Roles.LOCAL_HR_RICOH_PORTUGAL, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RPT,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RPT, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RPT,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RPT, List.of(new Locale("pt")), Roles.TALENT_ACQUISITION_RPT),
	RICOH_PORTUGAL_TOTALSTOR("1901", "RPTTS", Roles.LOCAL_HR_RICOH_PORTUGAL_TOTALSTOR, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RPTTS,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RPTTS, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RPTTS,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RPTTS, List.of(new Locale("pt")), Roles.TALENT_ACQUISITION_RPTTS),
	RICOH_BELGIUM("1200", "RBE", Roles.LOCAL_HR_RICOH_BELGIUM, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RBE,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RBE, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RBE,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RBE, List.of(new Locale("nl"), Locale.FRENCH), Roles.TALENT_ACQUISITION_RBE),
	RICOH_LUXEMBOURG("3000", "RLU", Roles.LOCAL_HR_RICOH_LUXEMBOURG, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RLU,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RLU, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RLU,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RLU, List.of(new Locale("nl"), Locale.FRENCH), Roles.TALENT_ACQUISITION_RLU),
	RICOH_ITALIA_SRL("1500", "RIT", Roles.LOCAL_HR_RICOH_ITALIA_SRL, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RIT,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RIT, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RIT,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RIT, List.of(Locale.ITALIAN), Roles.TALENT_ACQUISITION_RIT),
	RICOH_TURKEY("4700", "RTR", Roles.LOCAL_HR_RICOH_TURKEY, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RTR,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RTR, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RTR,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RTR, List.of(new Locale("tr")), Roles.TALENT_ACQUISITION_RTR),
	RICOH_CZECH_REPUBLIC("1800", "RCZ", Roles.LOCAL_HR_RICOH_CZECH_REPUBLIC, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RCZ,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RCZ, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RCZ,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RCZ, List.of(new Locale("cs")), Roles.TALENT_ACQUISITION_RCZ_RSK),
	RICOH_SLOVAKIA("3100", "RSK", Roles.LOCAL_HR_RICOH_SLOVAKIA, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RSK,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RSK, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RSK,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RSK, List.of(new Locale("cs")), Roles.TALENT_ACQUISITION_RCZ_RSK),
	RICOH_HUNGARY_KFT("2100", "RHU", Roles.LOCAL_HR_RICOH_HUNGARY_KFT, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RHU,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RHU, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RHU,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RHU, List.of(new Locale("hu")), Roles.TALENT_ACQUISITION_RHU),
	RICOH_AUSTRIA_GMBH("0300", "RAT", Roles.LOCAL_HR_RICOH_AUSTRIA_GMBH, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RAT,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RAT, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RAT,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RAT, List.of(Locale.GERMAN), Roles.TALENT_ACQUISITION_RAT),
	RICOH_DEUTSCHLAND_GMBH("0100", "RDE", Roles.LOCAL_HR_RICOH_DEUTSCHLAND_GMBH, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RDE,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RDE, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RDE,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RDE, List.of(Locale.GERMAN), Roles.TALENT_ACQUISITION_RDE),
	RICOH_INTERNATIONAL_BV("0101", "RIG", Roles.LOCAL_HR_RICOH_INTERNATIONAL_BV, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RIG,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RIG, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RIG,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RIG, List.of(Locale.GERMAN), Roles.TALENT_ACQUISITION_RIG),
	RICOH_SCHWEIZ_AG("0200", "RCH", Roles.LOCAL_HR_RICOH_SCHWEIZ_AG, "",
			LegalEntityNewHire.DEFAULT, Roles.PSO_CASE_OWNER_MAINTENANCE_CHECK_RCH,
			Roles.WFI_CASE_OWNER_MAINTENANCE_CHECK_RCH, Roles.LD_CASE_OWNER_MAINTENANCE_CHECK_RCH,
			Roles.TA_CASE_OWNER_MAINTENANCE_CHECK_RCH, List.of(Locale.GERMAN, Locale.FRENCH), Roles.TALENT_ACQUISITION_RCH),
	DEFAULT("", "", Roles.DEFAULT, "", LegalEntityNewHire.DEFAULT, Roles.DEFAULT,
			Roles.DEFAULT, Roles.DEFAULT, Roles.DEFAULT, List.of(Locale.ENGLISH), Roles.DEFAULT);

	private static final Map<String, LegalEntityEnum> LEGAL_ENTITY_MAP = new HashMap<>();
	private String legalEntityId;
	private String shortName;
	private String responsibleRole;
	private String requiredDocumentInfo;
	private LegalEntityNewHire dataCheck;
	private Roles pSOCaseOwnerRole;
	private Roles wFICaseOwnerRole;
	private Roles lDCaseOwnerRole;
	private Roles tACaseOwnerRole;
	private List<Locale> defaultLanguages;
	private Roles talentAcquisitionTaskRole;

	static {
		for (LegalEntityEnum entity : values()) {
			LEGAL_ENTITY_MAP.put(entity.legalEntityId, entity);
		}
	}

	private LegalEntityEnum(String legalEntityId, String shortName, Roles responsibleRole, String requiredDocumentInfo,
			LegalEntityNewHire dataCheck, Roles pSOCaseOwnerRole, Roles wFICaseOwnerRole,
			Roles lDCaseOwnerRole, Roles tACaseOwnerRole, List<Locale> defaultLanguages, Roles talentAcquisitionTaskRole) {
		this.shortName = shortName;
		this.legalEntityId = legalEntityId;
		this.responsibleRole = responsibleRole.getIvyRoleName();
		this.requiredDocumentInfo = requiredDocumentInfo;
		this.setDataCheck(dataCheck);
		this.pSOCaseOwnerRole = pSOCaseOwnerRole;
		this.wFICaseOwnerRole = wFICaseOwnerRole;
		this.lDCaseOwnerRole = lDCaseOwnerRole;
		this.tACaseOwnerRole = tACaseOwnerRole;
		this.defaultLanguages = defaultLanguages;
		this.talentAcquisitionTaskRole = talentAcquisitionTaskRole;
	}

	public LegalEntityNewHire getDataCheck() {
		return dataCheck;
	}

	public void setDataCheck(LegalEntityNewHire dataCheck) {
		this.dataCheck = dataCheck;
	}

	public String getResponsibleRole() {
		return responsibleRole;
	}

	public String getRequiredDocumentInfo() {
		return requiredDocumentInfo;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public static Roles getCaseOwnerRole(String id, CenterGroup centerGroup) {
		Roles role;
		switch (centerGroup) {
		case PEOPLE_SERVICES_OPERATIONS:
			role = valueOfLegalEntityId(id).getPSOCaseOwnerRole();
			break;
		case WORKFORCE_INSIGHTS:
			role = valueOfLegalEntityId(id).getWFICaseOwnerRole();
			break;
		case LEARNING_AND_DEVELOPMENT:
			role = valueOfLegalEntityId(id).getLDCaseOwnerRole();
			break;
		case TALENT_ACQUISITION:
			role = valueOfLegalEntityId(id).getTACaseOwnerRole();
			break;
		default:
			role = Roles.DEFAULT;
		}

		return role;
	}

	public static LegalEntityEnum valueOfLegalEntityId(String legalEntityId) {
		if (StringUtils.isBlank(legalEntityId)) {
			return DEFAULT;
		}
		return LEGAL_ENTITY_MAP.getOrDefault(legalEntityId, DEFAULT);
	}

	public static LegalEntityEnum valueOfLegalEntityIdOrDefault(String legalEntityId,
			LegalEntityEnum defaultLegalEntity) {
		LegalEntityEnum legalEntity = null;
		if (StringUtils.isNotBlank(legalEntityId)) {
			legalEntity = valueOfLegalEntityId(legalEntityId);
		}
		if (legalEntity == null) {
			legalEntity = defaultLegalEntity;
		}
		return legalEntity;
	}

	public FoObject toFoObject() {
		var legalEntity = new FoObject();
		legalEntity.setExternalCode(legalEntityId);
		legalEntity.setName(shortName);
		return legalEntity;
	}

	public String getShortName() {
		return shortName;
	}

	public Roles getPSOCaseOwnerRole() {
		return pSOCaseOwnerRole;
	}

	public Roles getWFICaseOwnerRole() {
		return wFICaseOwnerRole;
	}

	public Roles getLDCaseOwnerRole() {
		return lDCaseOwnerRole;
	}

	public Roles getTACaseOwnerRole() {
		return tACaseOwnerRole;
	}

	public List<Locale> getDefaultLanguages() {
		return defaultLanguages;
	}

	public void setDefaultLanguages(List<Locale> defaultLanguages) {
		this.defaultLanguages = defaultLanguages;
	}

	public Roles getTalentAcquisitionTaskRole() {
		return talentAcquisitionTaskRole;
	}

}
