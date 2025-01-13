package com.axonivy.utils.gdprconnector.model;

import java.util.Date;

import com.axonivy.utils.gdprconnector.enums.CustomField;
import com.axonivy.utils.gdprconnector.service.IvyTaskService;
import com.axonivy.utils.gdprconnector.utils.TaskStateUtils;

import ch.ivyteam.ivy.workflow.ITask;

public class RescheduleTask extends Task {
	private long taksId;
	private String caseName;
	private String taskState;
	private Date newExpiryDate;
	private Date reportingNewExpiryDate;
	private Date reportingOriginalExpiryDate;
	private boolean isRunningTask;

	public RescheduleTask() {
	}

	public RescheduleTask(ITask task) {
		setTaksId(task.getId());
		setCaseName(task.getCase().getName());
		setTaskName(task.getName());
		setTaskState(TaskStateUtils.getUserFriendlyTaskStateInCapitalization(task.getState()));
		setNewExpiryDate(task.customFields().timestampField(CustomField.NEW_EXPIRY_DATE.getFieldName()).getOrNull());
		setReportingNewExpiryDate(task.customFields().timestampField(CustomField.REPORTING_NEW_EXPIRY_DATE.getFieldName()).getOrNull());
		setReportingOriginalExpiryDate(task.customFields().timestampField(CustomField.REPORTING_ORIGINAL_EXPIRY_DATE.getFieldName()).getOrNull());
		setRunningTask(IvyTaskService.isNotDone(task));
		if (task.getExpiryTimestamp() != null) {
			setExpiryDate(new ch.ivyteam.ivy.scripting.objects.Date(task.getExpiryTimestamp()));
		}
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public Date getNewExpiryDate() {
		return newExpiryDate;
	}

	public void setNewExpiryDate(Date newExpiryDate) {
		this.newExpiryDate = newExpiryDate;
	}

	public Date getReportingNewExpiryDate() {
		return reportingNewExpiryDate;
	}

	public void setReportingNewExpiryDate(Date reportingNewExpiryDate) {
		this.reportingNewExpiryDate = reportingNewExpiryDate;
	}

	public Date getReportingOriginalExpiryDate() {
		return reportingOriginalExpiryDate;
	}

	public void setReportingOriginalExpiryDate(Date reportingOriginalExpiryDate) {
		this.reportingOriginalExpiryDate = reportingOriginalExpiryDate;
	}

	public long getTaksId() {
		return taksId;
	}

	public void setTaksId(long taksId) {
		this.taksId = taksId;
	}

	public boolean isRunningTask() {
		return isRunningTask;
	}

	public void setRunningTask(boolean isRunningTask) {
		this.isRunningTask = isRunningTask;
	}
}
