package com.axonivy.utils.gdprconnector.enums;

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
