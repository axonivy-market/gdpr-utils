package com.axonivy.utils.gdpr.service;

import java.util.EnumSet;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class IvyTaskService {
	private static final EnumSet<TaskState> workingStates = EnumSet.of(TaskState.RESUMED, TaskState.PARKED,
			TaskState.SUSPENDED, TaskState.CREATED, TaskState.DELAYED);
	private static final EnumSet<TaskState> technicalStates = EnumSet.of(TaskState.WAITING_FOR_INTERMEDIATE_EVENT,
			TaskState.FAILED, TaskState.JOIN_FAILED, TaskState.DESTROYED);

	public static boolean isNotDone(ITask task) {
		if (task == null) {
			return false;
		}
		return workingStates.contains(task.getState());
	}

	public static boolean isTechnicalState(ITask task) {
		if (task == null) {
			return false;
		}
		return technicalStates.contains(task.getState());
	}

	public static ITask findTask(long taskId) {
		return Sudo.get(() -> {
			return Ivy.wf().findTask(taskId);
		});
	}
}
