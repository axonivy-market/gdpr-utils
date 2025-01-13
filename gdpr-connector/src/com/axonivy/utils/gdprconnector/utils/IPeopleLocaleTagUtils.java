package com.axonivy.utils.gdprconnector.utils;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.enums.LocaleTag;

import ch.ivyteam.ivy.environment.Ivy;

public class IPeopleLocaleTagUtils {
	public static LocaleTag getLocaleTagBySessionContentLocale() {
		String locale = Ivy.session().getContentLocale().toString();
		return StringUtils.isBlank(locale) ? LocaleTag.DEFAULT_IVY_LOCALE : getLocaleTagByCode(locale);
	}

	public static LocaleTag getLocaleTagByCode(String contentLocale) {
		return Arrays.stream(LocaleTag.values()).filter(locale -> locale.getCode().equals(contentLocale)).findAny()
				.orElse(LocaleTag.DEFAULT_IVY_LOCALE);
	}
}
