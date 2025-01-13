package com.axonivy.utils.gdprconnector.utils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataFOJobCode;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;

import ch.ivyteam.ivy.environment.Ivy;

public class IPeopleJobCodeUtils {
	public static String getLocalizedName(SFODataFOJobCode jobCode) {
		String sessionLocale = Ivy.session().getContentLocale().toString();
		String localizedName = getNameByLocale(jobCode, sessionLocale);
		return StringUtils.defaultIfBlank(localizedName, StringUtils.EMPTY);
	}

	private static String getNameByLocale(SFODataFOJobCode jobCode, String locale) {
		String name;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			name = jobCode.getNameFiFI();
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			name = jobCode.getNameSvSE();
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			name = jobCode.getNameNlNL();
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			name = jobCode.getNameDaDK();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			name = jobCode.getNameDeDE();
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			name = jobCode.getNameEsES();
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			name = jobCode.getNamePlPL();
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			name = jobCode.getNamePtPT();
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			name = jobCode.getNameItIT();
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			name = jobCode.getNameFrFR();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			name = jobCode.getNameDeCH();
		} else if (locale.equalsIgnoreCase(LocaleTag.EN_GB.getCode())) {
			name = jobCode.getNameEnGB();
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			name = jobCode.getNameNbNO();
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			name = jobCode.getNameHuHU();
		} else {
			name = jobCode.getNameDefaultValue();
		}

		// TODO: SFODataFOJobCode misses names for locale sk_SK, cs_CZ
		return StringUtils.defaultIfBlank(name, jobCode.getNameLocalized());
	}
}
