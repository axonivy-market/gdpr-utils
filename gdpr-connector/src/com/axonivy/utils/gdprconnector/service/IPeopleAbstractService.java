package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.ACTIVE_FILTER;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AND;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.LIKE;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.LOWER_CASE_FILTER;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NAVIGATION_SEPARATOR;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.COUNT;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.EXPAND;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.FILTER;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.ORDER_BY;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.SELECT;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.SKIP;
import static com.axonivy.utils.gdprconnector.enums.IPeopleParam.TOP;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.bo.IPeopleCriteria;
import com.axonivy.utils.gdprconnector.enums.IPeopleObjectType;
import com.axonivy.utils.gdprconnector.managedbean.IPeopleCacheBean;
import com.axonivy.utils.gdprconnector.utils.IPeopleQueryUtils;

import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;

public abstract class IPeopleAbstractService<T extends Object, U extends Object> implements IPeopleService<T, U> {
	protected abstract IPeopleObjectType getType();
	protected abstract String getReturnParam();
	protected abstract String getStartName();
	protected abstract T mapResult(U value);
	protected abstract List<String> getDefaultSelection();
	protected abstract List<String> getDefaultOrderby();
	protected abstract List<String> getDefaultExpand();
	protected abstract boolean useActiveFilter(); 

	@Override
	public List<T> findById(String id) {
		return findByFilter(String.format(EQUAL_FILTER_PATTERN, getType().getIdField().getName(), id), 1);
	}

	@Override
	public List<T> findByName(String name, Integer top) {		
		var filterByName = format(LOWER_CASE_FILTER, buildName(getType())) + format(LIKE, StringUtils.lowerCase(name));

		return findByFilter(filterByName, top);
	}

	@Override
	public List<T> findByNameAndParent(String name, String parentId, Integer top) {
		var filterByName = format(LOWER_CASE_FILTER, buildName(getType())) + format(LIKE, StringUtils.lowerCase(name));

		return findByFilter(filterByName, top);
	}

	@Override
	public List<T> findByParentAndFilter(String parentId, String filter, Integer top) {
		return findByFilter(filter, top);
	}

	@Override
	public List<T> findByFilter(String filter, Integer top) {
		return callSub(filter, top, null);
	}

	@Override
	public List<T> findByCriteria(IPeopleCriteria criteria) {
		var filter = criteria.getFilter();
		if (StringUtils.isBlank(filter)) {
			filter = String.format(EQUAL_FILTER_PATTERN, getType().getIdField().getName(), criteria.getId());
		}
		return callSub(filter, criteria.getSelections(), criteria.getExpands(), criteria.getOrderby(),
				criteria.getCount(), criteria.getTop(), criteria.getSkip());
	}

	protected List<T> mapAllResults(List<U> objects) {
		List<T> result = new ArrayList<>();
		for (U d : objects) {
			var data = mapResult(d);
			if (data == null) {
				continue;
			}
			result.add(data);
		}

		if (FacesContext.getCurrentInstance() != null) {
			// Refresh cache for iPeople objects
			getCacheBean().refreshCache(objects, getServiceEntityKey());
			// Refresh cache for mapped objects
			getCacheBean().refreshCache(result, getServiceEntityKey());
		}
		return result;
	}

	@Override
	public List<T> callSub(String filter, Integer top, Integer skip) {
		return callSub(filter, getDefaultSelection(), getDefaultExpand(), getDefaultOrderby(), null, top, skip);
	}

	protected List<T> callSub(String filter, List<String> select, List<String> expand, List<String> orderby,
			Boolean count, Integer top, Integer skip) {
		List<U> callResult = callToIPeopleInterfaceWithParam(filter, select, expand, orderby, count, top, skip);
		return null != callResult ? mapAllResults(callResult) : new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	protected List<U> callToIPeopleInterfaceWithParam(String filter, List<String> select, List<String> expand,
			List<String> orderby, Boolean count, Integer top, Integer skip) {
		List<U> result = null;
		SubProcessCallResult callResult = callSubWithParams(addActiveFilter(filter), select, expand, orderby, count,
				top, skip);
		if (callResult != null) {
			Optional<Object> o = Optional.ofNullable(callResult.get(getReturnParam()));
			if (o.isPresent() && o.get() instanceof List<?> list) {
				result = (List<U>) list;
			}
		}
		return result;
	}

	protected SubProcessCallResult callSubWithParams(String filter, List<String> select, List<String> expand,
			List<String> orderby, Boolean count, Integer top, Integer skip) {
		var filterWithoutCaseSensitive = IPeopleQueryUtils.removeCaseSensitivityForFilter(filter);
		return SubProcessCall.withPath(getType().getSubprocessName()).withStartName(getStartName())
				.withParam(FILTER.getName(), filterWithoutCaseSensitive).withParam(SELECT.getName(), select)
				.withParam(EXPAND.getName(), expand).withParam(ORDER_BY.getName(), orderby)
				.withParam(COUNT.getName(), count).withParam(SKIP.getName(), skip).withParam(TOP.getName(), top).call();
	}

	protected String addActiveFilter(String filter) {
		List<String> filterBuilder = new ArrayList<>();
		if (useActiveFilter()) {
			filterBuilder.add(ACTIVE_FILTER);
		}
		if (StringUtils.isNotBlank(filter)) {
			filterBuilder.add(filter);
		}
		return !filterBuilder.isEmpty() ? filterBuilder.stream().collect(Collectors.joining(AND)) : null;
	}

	protected String buildName(IPeopleObjectType type) {
		String nav = Arrays.asList(type.getNameNav()).stream().map(e -> e.getName())
				.collect(Collectors.joining(NAVIGATION_SEPARATOR));
		if (StringUtils.isNotBlank(nav)) {
			nav += NAVIGATION_SEPARATOR;
		}
		nav += type.getNameField().getName();
		return nav;
	}

	@Override
	public String getServiceEntityKey() {
		return IPeopleCacheBean.generateServiceEntityClassKey(getClass().getTypeName());
	}

	protected void clearCacheBean(String beanName) {
		var serviceKey = IPeopleCacheBean.generateServiceEntityClassKey(beanName);
		getCacheBean().clearCacheContainKeyWord(serviceKey);
	}
}
