package com.axonivy.utils.gdprconnector.bo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class IPeopleCriteria {
	private String id;
	private String filter;
	private List<String> expands;
	private List<String> selections;
	private List<String> orderby;
	private Boolean count;
	private Integer top = 1;
	private Integer skip;

	public IPeopleCriteria() {
	}

	public IPeopleCriteria(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<String> getExpands() {
		if (CollectionUtils.isEmpty(expands)) {
			return ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		}
		return ch.ivyteam.ivy.scripting.objects.List.create(String.class, expands);
	}

	public void setExpands(String... expand) {
		this.expands = Arrays.asList(expand);
	}

	public List<String> getSelections() {
		if (CollectionUtils.isEmpty(selections)) {
			return ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		}
		return ch.ivyteam.ivy.scripting.objects.List.create(String.class, selections);
	}

	public void setSelections(String... selection) {
		this.selections = Arrays.asList(selection);
	}

	public List<String> getOrderby() {
		return orderby;
	}

	public void setOrderby(String... orderby) {
		this.orderby = Arrays.asList(orderby);
	}

	public Boolean getCount() {
		return count;
	}

	public void setCount(Boolean count) {
		this.count = count;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}
}
