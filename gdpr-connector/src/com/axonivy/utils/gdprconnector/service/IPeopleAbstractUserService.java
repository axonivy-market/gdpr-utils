package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AND;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.INSIDE_PARENTHESIS_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.OR;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

import java.util.List;
import java.util.function.Predicate;

import com.axonivy.utils.gdprconnector.bo.IPeopleCriteria;
import com.axonivy.utils.gdprconnector.bo.IPeopleUser;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;

public abstract class IPeopleAbstractUserService<U extends Object> extends IPeopleAbstractService<IPeopleUser, U> {
	@Override
	protected boolean useActiveFilter() {
		return false;
	}

	@Override
	public List<IPeopleUser> findById(String id) {
		return findByPersonIdExternalOrUserId(id, id);
	}

	@Override
	public List<IPeopleUser> findByCriteria(IPeopleCriteria criteria) {
		var filter = criteria.getFilter();
		if (isBlank(filter)) {
			filter = filterByPersonIdExternalOrUserId(criteria.getId(), criteria.getId());
		}
		var finalSelection = criteria.getSelections();
		finalSelection.addAll(getDefaultSelection());
		var finalExpands = criteria.getExpands();
		finalExpands.addAll(getDefaultExpand());
		return callSub(filter, finalSelection, finalExpands, criteria.getOrderby(), criteria.getCount(), criteria.getTop(),
				criteria.getSkip());
	}

	/**
	 * High priority: the personIdExternal is unique for employee. Low priority:
	 * search by userId to make the code backward-compatible
	 */
	protected List<IPeopleUser> findByPersonIdExternalOrUserId(String personIdExternal, String userId) {
		var filter = filterByPersonIdExternalOrUserId(personIdExternal, userId);
		var additionalFilter = createAdditionalFilter();
		if (isNoneBlank(additionalFilter)) {
			filter += AND + additionalFilter;
		}
		return super.findByFilter(filter, 1);
	}

	@Override
	public IPeopleUser findByExternalId(String customId) {
		if (isBlank(customId)) {
			return null;
		}
		U foundEntity = emptyIfNull(getCacheEntities()).stream()
				.filter(filterByPersonIdExternal(customId))
				.findAny().orElse(null);
		if (isNull(foundEntity)) {
			var foundUsers = findById(customId);
			return isEmpty(foundUsers) ? null : foundUsers.get(0);
		}
		return mapResult(foundEntity);
	}

	@Override
	public List<IPeopleUser> findByFilter(String filter, Integer top) {
		return super.findByFilter(addDefaultFilter(filter), top);
	}

	protected String createAdditionalFilter() {
		return EMPTY;
	}

	private String filterByPersonIdExternalOrUserId(String personId, String userId) {
		return format(INSIDE_PARENTHESIS_PATTERN, createPersonIdFilter(personId) + OR + createUserIdFilter(userId));
	}

	protected String createUserIdFilter(String userId) {
		return format(EQUAL_FILTER_PATTERN, IPeopleField.USERID.getName(), userId);
	}

	protected abstract List<U> getCacheEntities();

	protected abstract Predicate<? super U> filterByPersonIdExternal(String customId);

	protected abstract String createPersonIdFilter(String personIdExternal);

	protected abstract String addDefaultFilter(String filter);
}
