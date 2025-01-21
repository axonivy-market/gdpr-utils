package com.axonivy.utils.gdpr.task;

import com.axonivy.utils.gdpr.enums.CategoryPath;
import com.axonivy.utils.gdpr.enums.Roles;
import com.axonivy.utils.gdpr.model.Task;
import com.axonivy.utils.gdpr.service.IvyService;

import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class TaskBuilderFactory {
	public static Task forDataDeletionTask() {
		var task = new Task();
		task.setTaskName(IvyService.getCMSMacrosValue("/Processes/DataDeletion/Tasks/GermanyDataDeletion/name"));
		task.setTaskDescription(
				IvyService.getCMSMacrosValue("/Processes/DataDeletion/Tasks/GermanyDataDeletion/description"));
		task.setCategory(CategoryPath.DATA_DELETION.getPath());
		task.setTaskRole(Roles.LOCAL_HR_RICOH_DEUTSCHLAND_GMBH.getIvyRoleName());
		task.setTaskPriority(WorkflowPriority.NORMAL);
		return task;
	}
}
