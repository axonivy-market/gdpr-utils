package com.axonivy.utils.gdprconnector.utils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.service.IvyService;

import ch.ivyteam.ivy.workflow.TaskState;

public class TaskStateUtils {
	private TaskStateUtils() {
	}

	public static String getUserFriendlyTaskStateInCapitalization(TaskState state) {
		if (state == null) {
			return StringUtils.EMPTY;
		}

		if (isLegacyTaskState(state)) {
			return IvyService.getTranslateStringFromCms("/Enums/com/ricoh/hr/core/enums/TaskState/" + state);
		}

		return IvyService.getTranslateStringFromCms("/Enums/com/ricoh/hr/core/enums/TaskState/SYSTEM");
	}

	public static Boolean isLegacyTaskState(TaskState state) {
		return state == TaskState.SUSPENDED || state == TaskState.CREATED || state == TaskState.RESUMED
				|| state == TaskState.DONE || state == TaskState.PARKED || state == TaskState.DESTROYED
				|| state == TaskState.DELAYED || state == TaskState.READY_FOR_JOIN || state == TaskState.ZOMBIE;
	}
}
