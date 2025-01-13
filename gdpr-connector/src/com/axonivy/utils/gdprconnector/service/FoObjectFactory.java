package com.axonivy.utils.gdprconnector.service;

import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.LocaleUtils;

import com.axonivy.connector.successfactors.connector.rest.AllOfSFODataEmpJobCustomString30Nav;
import com.axonivy.connector.successfactors.connector.rest.AllOfSFODataEmpJobLocationNav;
import com.axonivy.connector.successfactors.connector.rest.AllOfSFODataFOCompanyCustLegalEntityToPYCtrlArea;
import com.axonivy.connector.successfactors.connector.rest.SFODataCountry;
import com.axonivy.connector.successfactors.connector.rest.SFODataCurrency;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustGOPYControllingArea;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustGOPYCostCentre;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustGOPersonnelArea;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustGOPersonnelSubarea;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustNonRicohLocation;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustSection;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustSection2;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustSection3;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustSection4;
import com.axonivy.connector.successfactors.connector.rest.SFODataCustServiceLine;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOBusinessUnit;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOCompany;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOCostCenter;
import com.axonivy.connector.successfactors.connector.rest.SFODataFODepartment;
import com.axonivy.connector.successfactors.connector.rest.SFODataFODivision;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOJobCode;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOLocation;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOPayGrade;
import com.axonivy.connector.successfactors.connector.rest.SFODataFOPayGroup;
import com.axonivy.connector.successfactors.connector.rest.SFODataMDFEnumValue;
import com.axonivy.connector.successfactors.connector.rest.SFODataPaymentMethodV3;
import com.axonivy.connector.successfactors.connector.rest.SFODataPickListValueV2;
import com.axonivy.connector.successfactors.connector.rest.SFODataPicklistLabel;
import com.axonivy.connector.successfactors.connector.rest.SFODataPicklistOption;
import com.axonivy.connector.successfactors.connector.rest.SFODataTerritory;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;
import com.axonivy.utils.gdprconnector.utils.IPeopleDepartmentUtils;
import com.axonivy.utils.gdprconnector.utils.IPeopleDivisionUtils;
import com.axonivy.utils.gdprconnector.utils.IPeopleJobCodeUtils;
import com.axonivy.utils.gdprconnector.utils.IPeopleLabelUtils;
import com.axonivy.utils.gdprconnector.utils.IPeopleLocaleTagUtils;
import com.axonivy.utils.gdprconnector.utils.IPeoplePickListValueV2Utils;

import ch.ivyteam.ivy.environment.Ivy;

public class FoObjectFactory {
	private final static String STATUS_ACTIVE = "ACTIVE";

	public static FoObject create() {
		return new FoObject();
	}

	public static FoObject createNameByLocalized(SFODataFODepartment department) {
		FoObject result = create();
		if (department != null) {
			result.setExternalCode(department.getExternalCode());
			result.setName(department.getNameLocalized());
		}
		return result;
	}

	public static FoObject create(SFODataFODepartment department) {
		FoObject result = create();
		if (department != null) {
			result.setExternalCode(department.getExternalCode());
			result.setName(IPeopleDepartmentUtils.getLocalizedName(department));
		}
		return result;
	}

	public static FoObject create(SFODataCustGOPYControllingArea area) {
		FoObject result = create();
		if (area != null) {
			result.setExternalCode(area.getExternalCode());
			result.setName(area.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataFOBusinessUnit department) {
		FoObject result = create();
		if (department != null) {
			result.setExternalCode(department.getExternalCode());
			result.setName(department.getName());
		}
		return result;
	}

	public static FoObject createNameByLocalized(SFODataFODivision division) {
		FoObject result = create();
		if (division != null) {
			result.setExternalCode(division.getExternalCode());
			result.setName(division.getNameLocalized());
		}
		return result;
	}

	public static FoObject create(SFODataFODivision division) {
		FoObject result = create();
		if (division != null) {
			result.setExternalCode(division.getExternalCode());
			result.setName(IPeopleDivisionUtils.getLocalizedName(division));
		}
		return result;
	}

	public static FoObject create(SFODataFOCostCenter costCenter) {
		FoObject result = create();
		if (costCenter != null) {
			result.setExternalCode(costCenter.getExternalCode());
			result.setName(costCenter.getName());
		}
		return result;
	}

	public static FoObject create(SFODataCustGOPYCostCentre globalCostCentre) {
		FoObject result = create();
		if (globalCostCentre != null) {
			result.setExternalCode(globalCostCentre.getExternalCode());
			result.setName(globalCostCentre.getCustGlobalCostCenter() + globalCostCentre.getCustBranchCode());
		}
		return result;
	}

	public static FoObject create(SFODataFOCompany company) {
		FoObject result = new FoObject();
		if (company != null) {
			result.setExternalCode(company.getExternalCode());
			result.setName(company.getName());
		}
		return result;
	}

	public static FoObject create(SFODataPicklistLabel label) {
		FoObject result = create();
		if (label != null) {
			result.setExternalCode(label.getPicklistOption().getExternalCode());
			result.setName(label.getLabel());
		}
		return result;
	}

	public static FoObject create(SFODataTerritory territory) {
		FoObject result = create();
		if (territory != null) {
			result.setExternalCode(territory.getTerritoryCode());
			result.setName(territory.getTerritoryName());
		}
		return result;
	}

	public static FoObject create(SFODataCountry country) {
		FoObject result = create();
		if (country != null) {
			result.setExternalCode(country.getCode());
			result.setName(country.getExternalNameDefaultValue());
		}
		return result;
	}
	
	public static FoObject createCountryFoObject(SFODataCountry country) {
		FoObject result = create();
		if (country != null) {
			result.setExternalCode(country.getCode());
			result.setName(getCountryNameLocalize(country));
		}
		return result;
	}

	public static String getCountryNameLocalize(SFODataCountry country) {
		LocaleTag tag = IPeopleLocaleTagUtils.getLocaleTagBySessionContentLocale();
		String localizePositionTitle = switch (tag) {
		case FI_FI -> country.getExternalNameFiFI();
		case SV_SE -> country.getExternalNameSvSE();
		case NL_NL -> country.getExternalNameNlNL();
		case DA_DK -> country.getExternalNameDaDK();
		case DE_DE -> country.getExternalNameDeDE();
		case ES_ES -> country.getExternalNameEsES();
		case PL_PL -> country.getExternalNamePlPL();
		case PT_PT -> country.getExternalNamePtPT();
		case IT_IT -> country.getExternalNameItIT();
		case FR_FR -> country.getExternalNameFrFR();
		case EN_GB -> country.getExternalNameEnGB();
		case NB_NO -> country.getExternalNameNbNO();
		case HU_HU -> country.getExternalNameHuHU();
		default -> country.getExternalNameDefaultValue();
		};
		return StringUtils.isBlank(localizePositionTitle) ? country.getExternalNameDefaultValue()
				: localizePositionTitle;
	}

	public static String getExternalNameLocalize() {
		LocaleTag tag = IPeopleLocaleTagUtils.getLocaleTagBySessionContentLocale();
		String localizeExternalNameTitle = switch (tag) {
		case FI_FI -> IPeopleField.EXTERNAL_NAME_FI_FI.getName();
		case SV_SE -> IPeopleField.EXTERNAL_NAME_SV_SE.getName();
		case NL_NL -> IPeopleField.EXTERNAL_NAME_NL_NL.getName();
		case DA_DK -> IPeopleField.EXTERNAL_NAME_DA_DK.getName();
		case DE_DE -> IPeopleField.EXTERNAL_NAME_DE_DE.getName();
		case ES_ES -> IPeopleField.EXTERNAL_NAME_ES_ES.getName();
		case PL_PL -> IPeopleField.EXTERNAL_NAME_PL_PL.getName();
		case PT_PT -> IPeopleField.EXTERNAL_NAME_PT_PT.getName();
		case IT_IT -> IPeopleField.EXTERNAL_NAME_IT_IT.getName();
		case FR_FR -> IPeopleField.EXTERNAL_NAME_FR_FR.getName();
		case EN_GB -> IPeopleField.EXTERNAL_NAME_EN_GB.getName();
		case NB_NO -> IPeopleField.EXTERNAL_NAME_NB_NO.getName();
		case HU_HU -> IPeopleField.EXTERNAL_NAME_HU_HU.getName();
		default -> IPeopleField.EXTERNALNAME_DEFAULTVALUE.getName();
		};
		return StringUtils.isBlank(localizeExternalNameTitle) ? IPeopleField.EXTERNALNAME_DEFAULTVALUE.getName()
				: localizeExternalNameTitle;
	}

	public static FoObject create(SFODataCurrency currency) {
		FoObject result = create();
		if (currency != null) {
			result.setExternalCode(currency.getCode());
			result.setName(currency.getExternalNameDefaultValue());
		}
		return result;
	}

	public static FoObject create(SFODataPaymentMethodV3 paymentMethod) {
		FoObject result = create();
		if (paymentMethod != null) {
			result.setExternalCode(paymentMethod.getExternalCode());
			result.setName(paymentMethod.getExternalNameDefaultValue());
		}
		return result;
	}

	public static FoObject create(SFODataFOPayGroup payGroup) {
		FoObject result = create();
		if (payGroup != null) {
			result.setExternalCode(payGroup.getExternalCode());
			result.setName(payGroup.getName());
		}
		return result;
	}

	public static FoObject create(SFODataMDFEnumValue enumValue) {
		FoObject result = create();
		if (enumValue != null) {
			String paymentMethodExternalName;
			LocaleTag locale = LocaleTag.fromName(IPeopleLocaleTagUtils.getLocaleTagBySessionContentLocale().getName());
			switch (locale) {
			case FI_FI:
				paymentMethodExternalName = enumValue.getFiFI();
				break;
			case SV_SE:
				paymentMethodExternalName = enumValue.getSvSE();
				break;
			case NL_NL:
				paymentMethodExternalName = enumValue.getNlNL();
				break;
			case DA_DK:
				paymentMethodExternalName = enumValue.getDaDK();
				break;
			case DE_CH:
				paymentMethodExternalName = enumValue.getDeCH();
				break;
			case ES_ES:
				paymentMethodExternalName = enumValue.getEsES();
				break;
			case PL_PL:
				paymentMethodExternalName = enumValue.getPlPL();
				break;
			case PT_PT:
				paymentMethodExternalName = enumValue.getPtPT();
				break;
			case IT_IT:
				paymentMethodExternalName = enumValue.getItIT();
				break;
			case FR_FR:
				paymentMethodExternalName = enumValue.getFrFR();
				break;
			case DE_DE:
				paymentMethodExternalName = enumValue.getDeDE();
				break;
			case EN_GB:
				paymentMethodExternalName = enumValue.getEnGB();
				break;
			case NB_NO:
				paymentMethodExternalName = enumValue.getNbNO();
				break;
			case HU_HU:
				paymentMethodExternalName = enumValue.getHuHU();
				break;
			default:
				paymentMethodExternalName = enumValue.getEnGB();
				break;
			}
			result.setExternalCode(enumValue.getValue());
			if (StringUtils.isEmpty(paymentMethodExternalName)) {
				paymentMethodExternalName = enumValue.getEnGB();
			}
			result.setName(paymentMethodExternalName);
		}
		return result;
	}

	public static FoObject create(AllOfSFODataEmpJobLocationNav location) {
		FoObject result = create();
		if (location != null) {
			result.setExternalCode(location.getExternalCode());
			result.setName(location.getName());
		}
		return result;
	}

	public static FoObject create(SFODataFOLocation value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getName());
		}
		return result;
	}

	public static FoObject create(SFODataPicklistOption picklistOption) {
		FoObject result = create();
		if (picklistOption != null && CollectionUtils.isNotEmpty(picklistOption.getPicklistLabels())) {
			var sessionLocale = Ivy.session().getContentLocale();
			result.setExternalCode(String.valueOf(picklistOption.getId()));
			var labelByUserSessionLocale = picklistOption.getPicklistLabels().stream()
					.filter(label -> compareBySessionLocale(sessionLocale, label)).findAny().orElse(null);
			if (labelByUserSessionLocale == null) {
				labelByUserSessionLocale = picklistOption.getPicklistLabels().get(0);
			}
			result.setName(labelByUserSessionLocale.getLabel());
		}
		return result;
	}

	public static FoObject create(SFODataPicklistOption picklistOption, String locale) {
		FoObject result = create();
		if (picklistOption != null && picklistOption.getStatus().equalsIgnoreCase(STATUS_ACTIVE)
				&& CollectionUtils.isNotEmpty(picklistOption.getPicklistLabels())) {
			result.setExternalCode(String.valueOf(picklistOption.getId()));
			SFODataPicklistLabel sFODataPicklistLabel = picklistOption.getPicklistLabels().stream()
					.filter(label -> label.getLocale().equalsIgnoreCase(locale)).findFirst().orElse(null);
			result.setName(sFODataPicklistLabel != null ? sFODataPicklistLabel.getLabel() : StringUtils.EMPTY);
		}
		return result;
	}

	private static boolean compareBySessionLocale(Locale sessionLocale, SFODataPicklistLabel label) {
		try {
			return LocaleUtils.toLocale(label.getLocale()) == sessionLocale;
		} catch (Exception e) {
			return false;
		}
	}

	public static FoObject create(SFODataCustServiceLine value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(AllOfSFODataFOCompanyCustLegalEntityToPYCtrlArea custLegalEntityToPYCtrlArea) {
		FoObject result = create();
		if (custLegalEntityToPYCtrlArea != null) {
			result.setExternalCode(custLegalEntityToPYCtrlArea.getExternalCode());
			result.setName(custLegalEntityToPYCtrlArea.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataPickListValueV2 value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(IPeoplePickListValueV2Utils.getLocalizedName(value));
		}
		return result;
	}

	public static FoObject create(SFODataFOPayGrade value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getName());
		}
		return result;
	}

	public static FoObject create(SFODataFOJobCode value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			String localizeName = IPeopleJobCodeUtils.getLocalizedName(value);
			result.setName(IPeopleLabelUtils.showNameAndCode(localizeName, value.getExternalCode()));
		}
		return result;
	}

	public static FoObject create(SFODataCustGOPersonnelArea value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustGOPersonnelSubarea value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustNonRicohLocation value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(AllOfSFODataEmpJobCustomString30Nav value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustSection value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustSection2 value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustSection3 value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}

	public static FoObject create(SFODataCustSection4 value) {
		FoObject result = create();
		if (value != null) {
			result.setExternalCode(value.getExternalCode());
			result.setName(value.getExternalName());
		}
		return result;
	}
}
