package com.axonivy.utils.gdprconnector.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.managedbean.IPeopleCacheBean;

public abstract class IPeopleFoObjectService<FoOjbect, U extends Object> extends IPeopleAbstractService<FoObject, U> {
	@Override
	protected List<String> getDefaultSelection() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.EXTERNAL_CODE.getName());
		result.add(IPeopleField.NAME.getName());
		result.add(IPeopleField.CREATED.getName());
		return result;
	}

	@Override
	protected List<String> getDefaultOrderby() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.NAME.getName());
		return result;
	}

	@Override
	protected List<String> getDefaultExpand() {
		return null;
	}

	@Override
	public FoObject findByExternalId(String customId) {
		if (StringUtils.isBlank(customId)) {
			return null;
		}
		var result = getCacheBean().findInCache(
				IPeopleCacheBean.generateServiceEntityClassKey(getClass().getTypeName()),
				FoObject.class);
		var entityInCache = CollectionUtils.emptyIfNull(result).stream()
				.filter(data -> StringUtils.equals(data.getExternalCode(), customId)).findAny().orElse(null);
		if (Objects.isNull(entityInCache)) {
			return super.findByExternalId(customId);
		}
		return entityInCache;
	}
}
