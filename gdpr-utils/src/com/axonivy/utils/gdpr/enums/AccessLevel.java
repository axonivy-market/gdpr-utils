package com.axonivy.utils.gdpr.enums;

public enum AccessLevel implements HasCmsName {
	ACCESS_LEVEL_1("1"), 
	ACCESS_LEVEL_2("2"),
	ACCESS_LEVEL_3("3"),
	ACCESS_LEVEL_4("4"),
	ACCESS_LEVEL_5("5"),
	ACCESS_LEVEL_6("6"),
	ACCESS_LEVEL_7("7"),
	ACCESS_LEVEL_8("8"),
	ACCESS_LEVEL_9("9");	
	
	private String level;
	
	AccessLevel(String level){
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
