package com.axonivy.utils.gdprconnector.enums;

public enum HRTicketLevel implements HasCmsName {
	HIGH, MEDIUM, LOW;

	public String getNameWithCategory() {
		return getCms("category") + " > " + getCms("name");
	}
}
