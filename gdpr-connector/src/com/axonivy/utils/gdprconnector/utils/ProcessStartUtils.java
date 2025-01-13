package com.axonivy.utils.gdprconnector.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

public class ProcessStartUtils {
	public static String buildUrl(String friendlyRequestPath, Map<String, String> params) {
		String requestPath = findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
		return url(params, requestPath);
	}

	public static String buildAbsoluteUrl(String friendlyRequestPath, Map<String, String> params) {
		String requestPath = findAbsoluteUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
		return url(params, requestPath);
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

	public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
		IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
		return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
	}

	public static String findAbsoluteUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
		IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
		return processStart != null ? processStart.getLink().getAbsolute() : StringUtils.EMPTY;
	}

	private static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
		return Sudo.get(() -> {
			IProcessStart processStart = getProcessStart(requestPath, Ivy.request().getProcessModelVersion());
			if (processStart != null) {
				return processStart;
			}
			List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance()
					.allOf(ISecurityContext.current());

			for (IApplication app : applicationsInSecurityContext) {
				IProcessStart findProcessStart = filterPMV(requestPath, app).findFirst().orElse(null);
				if (findProcessStart != null) {
					return findProcessStart;
				}
			}
			return null;
		});
	}

	private static Stream<IProcessStart> filterPMV(String requestPath, IApplication application) {
		return application.getProcessModelsSortedByName().stream().filter(pm -> isActive(pm))
				.map(IProcessModel::getReleasedProcessModelVersion).filter(pmv -> isActive(pmv))
				.map(p -> getProcessStart(requestPath, p)).filter(Objects::nonNull);
	}

	private static boolean isActive(IProcessModel processModel) {
		return processModel.getActivityState() == ActivityState.ACTIVE;
	}

	private static boolean isActive(IProcessModelVersion processModelVersion) {
		return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
	}

	private static IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
		return IWorkflowProcessModelVersion.of(processModelVersion)
				.findStartElementByUserFriendlyRequestPath(requestPath);
	}
}
