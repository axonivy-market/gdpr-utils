package com.axonivy.utils.gdprconnector.bo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
public class FoObject implements Serializable {
	private static final long serialVersionUID = 1991011968016216453L;

	private String externalCode;
	private String name;

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FoObject[externalCode=" + externalCode + "; name=" + name + "]";
	}
}
