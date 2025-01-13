package com.axonivy.utils.gdprconnector.enums;

import com.axonivy.utils.gdprconnector.service.IvyService;

public enum LegalEntityNewHire {
	RICOH_UK("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_UK/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_UK/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_UK/text"),
	RICOH_IRELAND("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_IRELAND/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_IRELAND/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_IRELAND/text"),
	RICOH_POLAND("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_POLAND/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_POLAND/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_POLAND/text"),
	RBS_POLAND("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RBS_POLAND/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RBS_POLAND/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RBS_POLAND/text"),
	RICOH_SWEDEN("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SWEDEN/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SWEDEN/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SWEDEN/text"),
	RICOH_NORWAY("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_NORWAY/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_NORWAY/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_NORWAY/text"),
	RIBV("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RIBV/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RIBV/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RIBV/text"),
	RICOH_SOUTH_AFRICA("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SOUTH_AFRICA/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SOUTH_AFRICA/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_SOUTH_AFRICA/text"),
	RICOH_EUROPE("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_EUROPE/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_EUROPE/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_EUROPE/text"),
	RICOH_FINLAND("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_FINLAND/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_FINLAND/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_FINLAND/text"),
	RICOH_DENMARK("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_DENMARK/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_DENMARK/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/RICOH_DENMARK/text"),
	DEFAULT("/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/DEFAULT/content"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/DEFAULT/link"
			,"/Enums/com/ricoh/hr/core/enums/NewHireDataCheck/DEFAULT/text");
	
	LegalEntityNewHire(String contentCms, String linkCms, String textCms) {
		this.contentCms = IvyService.getTranslateStringFromCms(contentCms);
		this.linkCms = IvyService.getTranslateStringFromCms(linkCms);
		this.textCms = IvyService.getTranslateStringFromCms(textCms);
	}
	
	private String contentCms;
	private String linkCms;
	private String textCms;
	public String getContentCms() {
		return contentCms;
	}
	public void setContentCms(String contentCms) {
		this.contentCms = contentCms;
	}
	public String getLinkCms() {
		return linkCms;
	}
	public void setLinkCms(String linkCms) {
		this.linkCms = linkCms;
	}
	public String getTextCms() {
		return textCms;
	}
	public void setTextCms(String textCms) {
		this.textCms = textCms;
	}
}
