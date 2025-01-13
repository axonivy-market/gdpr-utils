package com.axonivy.utils.gdprconnector.managedbean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.enums.IPeopleField;

public class IPeopleCacheBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Object, Set<Object>> fetchedEntityMap = new HashMap<>();
	private static final int IPEOPLE_PARAM_INDEX = 1;
	public static final String SERVICE_ENTITY_KEY_FORMAT = "%s__%s";
	public static final String SERVICE_KEY_LOCALE_PATTERN = "_locale\\((.*?)\\)";
	private static final String LOCALE = "locale";
	private static final String FO_OBJECT_PREFIX = "foObject";

	public void refreshCache(List<?> entities, String serviceKey) {
		if (fetchedEntityMap == null) {
			fetchedEntityMap = new HashMap<>();
		}
		Set<Object> results = new HashSet<>();
		if (fetchedEntityMap.containsKey(serviceKey)) {
			results = fetchedEntityMap.get(serviceKey);
		} else {
			clearCacheWhenChangingLocale(serviceKey);
		}
		results.addAll(entities);
		fetchedEntityMap.put(serviceKey, results);
	}

	private void clearCacheWhenChangingLocale(String serviceKey) {
		int localeSeparationIndex = serviceKey.indexOf(LOCALE);
		if (localeSeparationIndex > -1) {
			Pattern pattern = Pattern.compile(SERVICE_KEY_LOCALE_PATTERN);
			for (Object objKey : fetchedEntityMap.keySet()) {
				// This will get the locale from the key with format. Ex: "picklist(ABC)_locale(en)" 
				Matcher objKeyLocaleMatcher = pattern.matcher(objKey.toString());
				if ((objKey.toString().startsWith(IPeopleField.PICKLIST.getName())
						|| objKey.toString().startsWith(FO_OBJECT_PREFIX))
						&& objKeyLocaleMatcher.find()) {
					Matcher serviceKeyLocaleMatcher = pattern.matcher(serviceKey);
					if (serviceKeyLocaleMatcher.find()
							&& !serviceKeyLocaleMatcher.group(1).equals(objKeyLocaleMatcher.group(1))) {
						fetchedEntityMap.remove(objKey);
						return;
					}
				}
			}
		}
	}

	public Object findInCacheByConverter(FacesContext context, UIComponent component, String entityId,
			Converter converter, String serviceKey) {
		if (fetchedEntityMap.containsKey(serviceKey)) {
			var results = fetchedEntityMap.get(serviceKey);
			return CollectionUtils.emptyIfNull(results).stream().filter(Objects::nonNull)
					.filter(data -> StringUtils.equals(converter.getAsString(context, component, data), entityId))
					.findAny().orElse(null);
		}

		return null;
	}

	public <U> List<U> findInCache(String serviceKey, Class<U> entityClass) {
		if (fetchedEntityMap.containsKey(serviceKey)) {
			return CollectionUtils.emptyIfNull(fetchedEntityMap.get(serviceKey)).stream().filter(Objects::nonNull)
					.filter(data -> entityClass.isInstance(data)).map(data -> entityClass.cast(data))
					.collect(Collectors.toList());
		}
		return null;
	}

	public static String generateServiceEntityClassKey(String serviceName) {
		Class<?> serviceClass = null;
		try {
			serviceClass = Class.forName(serviceName);
		} catch (ClassNotFoundException e) {
			return "";
		}
		if (serviceClass.getGenericSuperclass() instanceof ParameterizedType) {
			final ParameterizedType type = (ParameterizedType) serviceClass.getGenericSuperclass();
			var actualTypeArguments = type.getActualTypeArguments();
			String entityClass = "";
			if (actualTypeArguments != null && actualTypeArguments.length > 0) {
				entityClass = actualTypeArguments[0].getTypeName();
				if (actualTypeArguments.length > 1) {
					entityClass = actualTypeArguments[IPEOPLE_PARAM_INDEX].getTypeName();
				}
			}
			return String.format(IPeopleCacheBean.SERVICE_ENTITY_KEY_FORMAT, serviceClass.getTypeName(), entityClass);
		}

		return serviceClass.getTypeName();
	}

	public void clearCacheContainKeyWord(String keyName) {
		if (null != fetchedEntityMap) {
			fetchedEntityMap.remove(keyName);
		}
	}
}
