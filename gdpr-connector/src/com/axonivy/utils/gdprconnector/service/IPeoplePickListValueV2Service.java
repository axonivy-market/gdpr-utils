package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.AND;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.NOT_EQUAL_FILTER_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.INSIDE_PARENTHESIS_PATTERN;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.OR;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.SUBSTRING_LOWER_FILTER_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.successfactors.connector.rest.SFODataPickListValueV2;
import com.axonivy.utils.gdprconnector.bo.FoObject;
import com.axonivy.utils.gdprconnector.constant.IPeopleConstants;
import com.axonivy.utils.gdprconnector.enums.IPeopleField;
import com.axonivy.utils.gdprconnector.enums.IPeopleObjectType;
import com.axonivy.utils.gdprconnector.enums.IPeoplePicklistId;
import com.axonivy.utils.gdprconnector.utils.IPeoplePickListValueV2Utils;

import ch.ivyteam.ivy.environment.Ivy;

public class IPeoplePickListValueV2Service extends IPeopleFoObjectService<com.axonivy.utils.gdprconnector.bo.FoObject, SFODataPickListValueV2>{
	private String picklistId;
	private List<String> excludedCodes;
	
	public IPeoplePickListValueV2Service() {
	}

	public IPeoplePickListValueV2Service(IPeoplePicklistId picklistIdEnum) {
		this.picklistId = picklistIdEnum.getPicklistName();
	}

	public IPeoplePickListValueV2Service(IPeoplePicklistId picklistIdEnum, List<String> excludedCodes) {
		this.picklistId = picklistIdEnum.getPicklistName();
		this.excludedCodes = excludedCodes;
	}

	@Override
	protected IPeopleObjectType getType() {
		return IPeopleObjectType.PICKLIST_VALUE_V2;
	}

	@Override
	protected String getReturnParam() {
		return "pickListValueV2";
	}

	@Override
	protected String getStartName() {
		return "getPickListValueV2";
	}

	@Override
	protected FoObject mapResult(SFODataPickListValueV2 value) {
		return FoObjectFactory.create(value);
	}

	@Override
	protected boolean useActiveFilter() {
		return true;
	}

	@Override
	public List<FoObject> findByName(String label, Integer top) {
		String filterQuery = String.join(AND, generatePicklistIdFilter(), generateLabelLocalizedFilter(label));
		if (ObjectUtils.isNotEmpty(excludedCodes)) {
			String excludedCodeFilter = excludedCodes.stream().map(code -> generateExcludedCodeFilter(code))
					.collect(Collectors.joining(AND));
			filterQuery = String.join(AND, filterQuery, excludedCodeFilter);
		}
		return findByFilter(filterQuery, top);
	}

	@Override
	public List<FoObject> findByNameAndParent(String label, String parentPickListValue, Integer top) {
		String filterQuery = String.join(AND, generatePicklistIdFilter(),
				generateParentPickListValueFilter(parentPickListValue), generateLabelLocalizedFilter(label));
		if (ObjectUtils.isNotEmpty(excludedCodes)) {
			String excludedCodeFilter = excludedCodes.stream().map(code -> generateExcludedCodeFilter(code))
					.collect(Collectors.joining(AND));
			filterQuery = String.join(AND, filterQuery, excludedCodeFilter);
		}
		return findByFilter(filterQuery, top);
	}

	@Override
	protected List<String> getDefaultSelection() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeopleField.EXTERNAL_CODE.getName());
		result.add(IPeopleField.LABEL_FI_FI.getName());
		result.add(IPeopleField.LABEL_SV_SE.getName());
		result.add(IPeopleField.LABEL_NL_NL.getName());
		result.add(IPeopleField.LABEL_DA_DK.getName());
		result.add(IPeopleField.LABEL_DE_DE.getName());
		result.add(IPeopleField.LABEL_ES_ES.getName());
		result.add(IPeopleField.LABEL_PL_PL.getName());
		result.add(IPeopleField.LABEL_PT_PT.getName());
		result.add(IPeopleField.LABEL_IT_IT.getName());
		result.add(IPeopleField.LABEL_FR_FR.getName());
		result.add(IPeopleField.LABEL_DE_CH.getName());
		result.add(IPeopleField.LABEL_EN_GB.getName());
		result.add(IPeopleField.LABEL_NB_NO.getName());
		result.add(IPeopleField.LABEL_HU_HU.getName());
		result.add(IPeopleField.LABEL_DEFAULT.getName());
		return result;
	}

	@Override
	protected List<String> getDefaultOrderby() {
		List<String> result = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		result.add(IPeoplePickListValueV2Utils.getLocalizedFieldLabel().getName());
		return result;
	}

	@Override
	public List<FoObject> findById(String id) {
		String filterString = generateExternalCodeFilter(id);
		return findByFilter(filterString, 1);
	}
	
	public FoObject findByExternalCode(String externalCode) {
		if (StringUtils.isBlank(externalCode)) {
			return null;
		}

		var cache = getCache();
		var entityInCache = CollectionUtils.emptyIfNull(cache).stream()
				.filter(data -> StringUtils.equals(data.getExternalCode(), externalCode)).findAny().orElse(null);
		if (Objects.isNull(entityInCache)) {
			var result = findById(externalCode);
			return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
		}
		return entityInCache;
	}

	private List<FoObject> getCache() {
		return getCacheBean().findInCache(getServiceEntityKey(), FoObject.class);
	}

	protected String generatePicklistIdFilter() {
		return String.format(EQUAL_FILTER_PATTERN, IPeopleField.PICKLIST_V2_ID.getName(), getPicklistId());
	}

	protected String generateExcludedCodeFilter(String code) {
		return String.format(NOT_EQUAL_FILTER_PATTERN, getType().getIdField().getName(), code);
	}
	
	private String generateParentPickListValueFilter(String parentPickListValue) {
		parentPickListValue = IPeoplePickListValueV2Utils
					.encodeKeyWordWithSpecialCharacter(parentPickListValue);
		return String.format(EQUAL_FILTER_PATTERN, IPeopleField.PARENT_PICKLIST_VALUE.getName(), parentPickListValue);
	}

	private String generateLabelLocalizedFilter(String label) {
		String subStringLabelLocalizedFilter = String.format(SUBSTRING_LOWER_FILTER_PATTERN, label,
				IPeoplePickListValueV2Utils.getLocalizedFieldLabel().getName());
		String subStringLabelDefaultFilter = String.format(SUBSTRING_LOWER_FILTER_PATTERN, label,
				IPeopleField.LABEL_DEFAULT.getName());
		return String.format(INSIDE_PARENTHESIS_PATTERN,
				subStringLabelLocalizedFilter + OR + subStringLabelDefaultFilter);
	}

	@Override
	public String getServiceEntityKey() {
		return String.format(IPeopleConstants.PICKLIST_WITH_LOCALE, this.picklistId,
				Ivy.session().getContentLocale().toLanguageTag());
	}

	@Override
	protected List<FoObject> mapAllResults(List<SFODataPickListValueV2> objects) {
		if (IPeoplePicklistId.DEFAULT.getPicklistName().equals(this.picklistId)) {
			return new ArrayList<>();
		}
		return super.mapAllResults(objects);
	}

	protected String generateExternalCodeFilter(String code) {
		code = IPeoplePickListValueV2Utils.encodeKeyWordWithSpecialCharacter(code);
		return generatePicklistIdFilter() + AND
				+ String.format(EQUAL_FILTER_PATTERN, getType().getIdField().getName(), code);
	}


	public String getOptionIdByExternalCode(String enxternalCode) {
		String filter = generateExternalCodeFilter(enxternalCode);
		return getOptionIdByFilter(filter);
	}

	public String getOptionIdByExternalCodeAndParrentField(String enxternalCode, String parrentField) {
		if (StringUtils.isBlank(enxternalCode) || StringUtils.isBlank(parrentField)) {
			return null;
		}
		String filter = generateExternalCodeFilter(enxternalCode) + AND
				+ generateParentPickListValueFilter(parrentField);
		return getOptionIdByFilter(filter);
	}

	public String getOptionIdByNameAndParrentield(String keyword, String parrentField) {
		if (StringUtils.isBlank(keyword) || StringUtils.isBlank(parrentField)) {
			return null;
		}
		String filter = generateLabelLocalizedFilter(keyword) + AND 
				+ generatePicklistIdFilter() + AND + generateParentPickListValueFilter(parrentField);
		return getOptionIdByFilter(filter);
	}

	public String getOptionIdByName(String keyword) {
		if (StringUtils.isBlank(keyword)) {
			return null;
		}
		String filter = generatePicklistIdFilter() + AND + generateLabelLocalizedFilter(keyword);
		return getOptionIdByFilter(filter);
	}

	private String getOptionIdByFilter(String filterString) {
		List<String> selection = ch.ivyteam.ivy.scripting.objects.List.create(String.class);
		selection.add(IPeopleField.OPTION_ID.getName());
		List<SFODataPickListValueV2> callResult = callToIPeopleInterfaceWithParam(filterString, selection, null, null, null, 1, null);
		return CollectionUtils.isEmpty(callResult) ? null : callResult.get(0).getOptionId();
	}

	public String getPicklistId() {
		return picklistId;
	}

	public void setPicklistId(String picklistId) {
		this.picklistId = picklistId;
	}
}
