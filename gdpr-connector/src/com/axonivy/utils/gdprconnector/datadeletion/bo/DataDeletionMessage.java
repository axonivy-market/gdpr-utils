package com.axonivy.utils.gdprconnector.datadeletion.bo;

import java.io.Serializable;

public class DataDeletionMessage implements Serializable {
	private static final long serialVersionUID = -7447307841500237995L;
	private long caseId;
	private String message;

	public DataDeletionMessage() {
		super();
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
