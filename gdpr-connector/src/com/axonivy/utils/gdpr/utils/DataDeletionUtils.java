package com.axonivy.utils.gdpr.utils;

import static com.axonivy.utils.gdpr.constant.Constants.ID;
import static com.axonivy.utils.gdpr.enums.CustomField.EMBED_IN_FRAME;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class DataDeletionUtils {

	public static String getDataDeleteionCaseDetailUrl(String id) {
		Map<String, String> params = initRequestParam();
		addIdParam(id, params);
		var requestPath = Ivy.html().startInFrameLink("Business Processes/DataDeletionDetails/businessCaseDetails.ivp")
				.getRelative();
		return url(params, requestPath);
	}

	private static Map<String, String> initRequestParam() {
		Map<String, String> params = new HashMap<>();
		params.put(EMBED_IN_FRAME.getFieldName(), "true");
		return params;
	}

	private static void addIdParam(String id, Map<String, String> params) {
		params.put(ID, id);
	}

	private static String url(Map<String, String> params, String requestPath) {
		if (StringUtils.isEmpty(requestPath)) {
			return StringUtils.EMPTY;
		}
		String paramStr = params.entrySet().stream().map(e -> {
			return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
		}).collect(Collectors.joining("&"));
		return requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
	}
}
