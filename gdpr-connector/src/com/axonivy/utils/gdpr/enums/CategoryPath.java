package com.axonivy.utils.gdpr.enums;

public enum CategoryPath {
	DATA_DELETION("dataDeletion");

	private String path;

	private CategoryPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
