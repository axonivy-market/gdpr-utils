package com.axonivy.utils.gdprconnector.utils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataFODivision;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;

public class IPeopleDivisionUtils {
	public static String getLocalizedName(SFODataFODivision division) {
		String sessionLocale = IPeopleNameLocalizedUtils.getSessionLocale();
		String localizedName = getNameByLocale(division, sessionLocale);
		return StringUtils.defaultIfBlank(localizedName, StringUtils.EMPTY);
	}

	private static String getNameByLocale(SFODataFODivision division, String locale) {
		String name;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			name = division.getNameFiFI();
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			name = division.getNameSvSE();
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			name = division.getNameNlNL();
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			name = division.getNameDaDK();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			name = division.getNameDeDE();
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			name = division.getNameEsES();
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			name = division.getNamePlPL();
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			name = division.getNamePtPT();
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			name = division.getNameItIT();
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			name = division.getNameFrFR();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			name = division.getNameDeCH();
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			name = division.getNameNbNO();
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			name = division.getNameHuHU();
		} else {
			name = division.getNameEnGB();
		}

		String finalResult = StringUtils.defaultIfBlank(name, division.getNameEnGB());
		// TODO: SFODataFODivision misses names for locale sk_SK, cs_CZ
		return StringUtils.defaultIfBlank(finalResult, division.getNameDefaultValue());
	}
}
