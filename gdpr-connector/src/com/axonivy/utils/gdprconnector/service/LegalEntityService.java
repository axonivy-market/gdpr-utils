package com.axonivy.utils.gdprconnector.service;

import java.util.List;

import com.axonivy.utils.gdprconnector.bo.FoObject;


public class LegalEntityService {
	private static IPeopleLegalEntityService service = new IPeopleLegalEntityService();

	public static FoObject findById(String id) {
		return service.findByExternalId(id);
	}

	public static List<FoObject> findByIds(List<String> ids) {
		return service.findByExternalCodes(ids);
	}

	public static List<FoObject> findAll() {
		return service.findAll();
	}

	public static List<FoObject> findByKeywordOfCompanyName(String keyword) {
		return service.findByShortName(keyword);
	}
}
