package com.axonivy.utils.gdprconnector.service;

import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.connector.successfactors.connector.rest.SFODataFOCompany;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.IPeopleObjectType;

public class IPeopleLegalEntityService extends IPeopleFoObjectService<FoObject, SFODataFOCompany> {
	@Override
	protected IPeopleObjectType getType() {
		return IPeopleObjectType.LEGAL_ENTITY;
	}

	@Override
	protected List<String> getDefaultSelection() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.NAME.getName());
		result.add(IPeopleField.EXTERNAL_CODE.getName());
		return result;
	}

	@Override
	protected String getReturnParam() {
		return "legalEnties";
	}

	@Override
	protected String getStartName() {
		return "getLegalEntities";
	}

	@Override
	protected FoObject mapResult(SFODataFOCompany value) {
		return FoObjectFactory.create(value);
	}

	@Override
	protected boolean useActiveFilter() {
		return true;
	}

	@Override
	public List<FoObject> findById(String code) {
		return super.findByFilter(IPeopleField.EXTERNAL_CODE.getName() + " eq '" + code + "'", 1);
	}

	public List<FoObject> findAll() {
		return super.findByFilter(null, null);
	}

	public List<FoObject> findByShortName(String shortName) {
		return super.findByFilter(IPeopleField.COUNTRY.getName() + " eq '" + shortName + "'", null);
	}

	public List<FoObject> findByExternalCodes(List<String> codes) {
		String filterValue = codes.stream().map(code -> String.format("'%s'", code)).collect(Collectors.joining(","));
		return super.findByFilter(IPeopleField.EXTERNAL_CODE.getName() + " in " + filterValue, null);
	}
}
