package com.axonivy.utils.gdprconnector.utils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataFODepartment;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;

public class IPeopleDepartmentUtils {
	public static String getLocalizedName(SFODataFODepartment department) {
		String sessionLocale = IPeopleNameLocalizedUtils.getSessionLocale();
		String localizedName = getNameByLocale(department, sessionLocale);
		return StringUtils.defaultIfBlank(localizedName, StringUtils.EMPTY);
	}

	private static String getNameByLocale(SFODataFODepartment department, String locale) {
		String name;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			name = department.getNameFiFI();
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			name = department.getNameSvSE();
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			name = department.getNameNlNL();
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			name = department.getNameDaDK();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			name = department.getNameDeDE();
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			name = department.getNameEsES();
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			name = department.getNamePlPL();
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			name = department.getNamePtPT();
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			name = department.getNameItIT();
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			name = department.getNameFrFR();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			name = department.getNameDeCH();
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			name = department.getNameNbNO();
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			name = department.getNameHuHU();
		} else {
			name = department.getNameEnGB();
		}
		String finalResult = StringUtils.defaultIfBlank(name, department.getNameEnGB());
		// TODO: SFODataFODivision misses names for locale sk_SK, cs_CZ
		return StringUtils.defaultIfBlank(finalResult, department.getNameDefaultValue());
	}
}
