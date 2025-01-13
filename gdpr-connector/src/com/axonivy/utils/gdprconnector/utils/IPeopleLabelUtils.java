package com.axonivy.utils.gdprconnector.utils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.bo.FoObject;


public class IPeopleLabelUtils {
	private static final String EMPTY_NAME_AND_CODE_FORMAT = "(%s)";
	private static final String NAME_AND_CODE_FORMAT = "%s (%s)";

	public static String showNameAndCode(FoObject value) {
		if (value == null || StringUtils.isBlank(value.getExternalCode())) {
			return StringUtils.EMPTY;
		}

		return showNameAndCode(value.getName(), value.getExternalCode());
	}

	public static String showNameAndCode(String name, String code) {
		if (StringUtils.isBlank(name)) {
			return String.format(EMPTY_NAME_AND_CODE_FORMAT, code);
		}
		return String.format(NAME_AND_CODE_FORMAT, name, code);
	}

}
