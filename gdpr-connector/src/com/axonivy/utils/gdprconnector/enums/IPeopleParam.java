package com.axonivy.utils.gdprconnector.enums;

public enum IPeopleParam {
	FILTER("filter"), SELECT("select"), EXPAND("expand"), ORDER_BY("orderby"), COUNT("count"), SKIP("skip"), TOP("top");

	private String name;

	private IPeopleParam(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
