package com.axonivy.utils.gdprconnector.service;

import java.util.List;
import java.util.Optional;

import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.bo.IPeopleCriteria;
import com.axonivy.utils.gdprconnector.managedbean.IPeopleCacheBean;

public interface IPeopleService<T extends Object, U extends Object> {
	public List<T> findById(String id);
	public List<T> findByName(String name, Integer top);
	public List<T> findByFilter(String filter, Integer top);
	public List<T> findByNameAndParent(String searchText, String parentId, Integer top);
	public List<T> findByParentAndFilter(String parentId, String filter, Integer top);
	public List<T> callSub(String filter, Integer top, Integer skip);

	default T findByExternalId(String customId) {
		if (StringUtils.isBlank(customId)) {
			return null;
		}
		var results = findById(customId);
		return CollectionUtils.isNotEmpty(results) ? results.get(0) : null;
	}

	default IPeopleCacheBean getCacheBean() {
		Optional<IPeopleCacheBean> cacheBean = Optional.empty();
		final var context = FacesContext.getCurrentInstance();
		if (context != null) {
			cacheBean = Optional.ofNullable(context.getApplication().evaluateExpressionGet(context,
					"#{iPeopleCacheBean}", IPeopleCacheBean.class));
		}
		return cacheBean.isPresent() ? cacheBean.get() : new IPeopleCacheBean();
	}

	public List<T> findByCriteria(IPeopleCriteria criteria);

	public String getServiceEntityKey();
}
