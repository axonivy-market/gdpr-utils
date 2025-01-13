package com.axonivy.utils.gdprconnector.datadeletion.task;

import com.axonivy.utils.gdprconnector.enums.CategoryPath;
import com.axonivy.utils.gdprconnector.enums.Roles;
import com.axonivy.utils.gdprconnector.model.Task;
import com.axonivy.utils.gdprconnector.service.IvyService;

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
