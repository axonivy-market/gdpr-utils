package com.axonivy.utils.gdprconnector.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataPickListValueV2;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.LocaleTag;

import ch.ivyteam.ivy.environment.Ivy;

public class IPeoplePickListValueV2Utils {
	public static String getLocalizedName(SFODataPickListValueV2 pickListValueV2) {
		String sessionLocale = getSessionLocale();
		String localizedName = getNameByLocale(pickListValueV2, sessionLocale);
		return StringUtils.defaultIfBlank(localizedName, StringUtils.EMPTY);
	}

	public static IPeopleField getLocalizedFieldLabel() {
		String sessionLocale = getSessionLocale();
		return getFieldLabelByLocale(sessionLocale);
	}

	public static String encodeKeyWordWithSpecialCharacter(String text) {
		if (StringUtils.isNotBlank(text)) {
			try {
				text = URLEncoder.encode(text, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				Ivy.log().warn(e.getMessage());
			}
		}
		return text;
	}
	private static String getNameByLocale(SFODataPickListValueV2 pickListValueV2, String locale) {
		String name;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			name = pickListValueV2.getLabelFiFI();
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			name = pickListValueV2.getLabelSvSE();
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			name = pickListValueV2.getLabelNlNL();
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			name = pickListValueV2.getLabelDaDK();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			name = pickListValueV2.getLabelDeDE();
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			name = pickListValueV2.getLabelEsES();
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			name = pickListValueV2.getLabelPlPL();
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			name = pickListValueV2.getLabelPtPT();
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			name = pickListValueV2.getLabelItIT();
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			name = pickListValueV2.getLabelFrFR();
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			name = pickListValueV2.getLabelDeCH();
		} else if (locale.equalsIgnoreCase(LocaleTag.EN_GB.getCode())) {
			name = pickListValueV2.getLabelEnGB();
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			name = pickListValueV2.getLabelNbNO();
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			name = pickListValueV2.getLabelHuHU();
		} else {
			name = pickListValueV2.getLabelDefaultValue();
		}

		// TODO: SFODataPickListValueV2 misses names for locale sk_SK, cs_CZ
		return StringUtils.defaultIfBlank(name, pickListValueV2.getLabelDefaultValue());
	}

	private static IPeopleField getFieldLabelByLocale(String locale) {
		IPeopleField fieldName;
		if (locale.equalsIgnoreCase(LocaleTag.FI_FI.getCode())) {
			fieldName = IPeopleField.LABEL_FI_FI;
		} else if (locale.equalsIgnoreCase(LocaleTag.SV_SE.getCode())) {
			fieldName = IPeopleField.LABEL_SV_SE;
		} else if (locale.equalsIgnoreCase(LocaleTag.NL_NL.getCode())) {
			fieldName = IPeopleField.LABEL_NL_NL;
		} else if (locale.equalsIgnoreCase(LocaleTag.DA_DK.getCode())) {
			fieldName = IPeopleField.LABEL_DA_DK;
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_DE.getCode())) {
			fieldName = IPeopleField.LABEL_DE_DE;
		} else if (locale.equalsIgnoreCase(LocaleTag.ES_ES.getCode())) {
			fieldName = IPeopleField.LABEL_ES_ES;
		} else if (locale.equalsIgnoreCase(LocaleTag.PL_PL.getCode())) {
			fieldName = IPeopleField.LABEL_PL_PL;
		} else if (locale.equalsIgnoreCase(LocaleTag.PT_PT.getCode())) {
			fieldName = IPeopleField.LABEL_PT_PT;
		} else if (locale.equalsIgnoreCase(LocaleTag.IT_IT.getCode())) {
			fieldName = IPeopleField.LABEL_IT_IT;
		} else if (locale.equalsIgnoreCase(LocaleTag.FR_FR.getCode())) {
			fieldName = IPeopleField.LABEL_FR_FR;
		} else if (locale.equalsIgnoreCase(LocaleTag.DE_CH.getCode())) {
			fieldName = IPeopleField.LABEL_DE_CH;
		} else if (locale.equalsIgnoreCase(LocaleTag.NB_NO.getCode())) {
			fieldName = IPeopleField.LABEL_NB_NO;
		} else if (locale.equalsIgnoreCase(LocaleTag.HU_HU.getCode())) {
			fieldName = IPeopleField.LABEL_HU_HU;
		} else {
			fieldName = IPeopleField.LABEL_DEFAULT;
		}

		// TODO: SFODataPickListValueV2 misses names for locale sk_SK, cs_CZ
		return fieldName;
	}

	private static String getSessionLocale() {
		return Ivy.session().getContentLocale().toString();
	}
}
