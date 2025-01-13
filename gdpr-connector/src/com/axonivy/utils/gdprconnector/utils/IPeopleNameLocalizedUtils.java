package com.axonivy.utils.gdprconnector.utils;

import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;

import ch.ivyteam.ivy.environment.Ivy;

public class IPeopleNameLocalizedUtils {
	public static IPeopleField getLocalizedFieldName() {
		String sessionLocale = getSessionLocale();
		return getFieldNameByLocale(sessionLocale);
	}

	private static IPeopleField getFieldNameByLocale(String locale) {
		IPeopleField fieldName;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			fieldName = IPeopleField.NAME_FI_FI;
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			fieldName = IPeopleField.NAME_SV_SE;
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			fieldName = IPeopleField.NAME_NL_NL;
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			fieldName = IPeopleField.NAME_DA_DK;
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			fieldName = IPeopleField.NAME_DE_DE;
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			fieldName = IPeopleField.NAME_ES_ES;
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			fieldName = IPeopleField.NAME_PL_PL;
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			fieldName = IPeopleField.NAME_PT_PT;
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			fieldName = IPeopleField.NAME_IT_IT;
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			fieldName = IPeopleField.NAME_FR_FR;
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			fieldName = IPeopleField.NAME_DE_CH;
		} else if (locale.equalsIgnoreCase(LocaleTag.EN_GB.getCode())) {
			fieldName = IPeopleField.NAME_EN_GB;
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			fieldName = IPeopleField.NAME_NB_NO;
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			fieldName = IPeopleField.NAME_HU_HU;
		} else {
			fieldName = IPeopleField.NAME_DEFAULT;
		}
		// TODO: misses names for locale sk_SK, cs_CZ
		return fieldName;
	}

	public static String getSessionLocale() {
		return Ivy.session().getContentLocale().toString();
	}
}
