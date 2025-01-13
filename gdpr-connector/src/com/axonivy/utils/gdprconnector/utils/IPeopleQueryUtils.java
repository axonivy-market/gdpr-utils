package com.axonivy.utils.gdprconnector.utils;

import static java.lang.String.format;
import static java.lang.String.join;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.utils.gdprconnector.constant.IPeopleConstants;

public class IPeopleQueryUtils {
	private static final String LIKE = "like";
	private static final String OR = "or";
	private static final String AND = "and";
	private static final String EQ = "eq";
	private static final String NE = "ne";
	private static final String PERCENT = "%25";
	private static final String IGNORE_REGEX = "(datetime|substringof|tolower|lt|gt|in|null|key)";
	private static final String NUMBER_REGEX = ".*\\d.*";
	private static final String PLUS_AND_WHITE_SPACE_REGEX = "[+\\s\\n]+";
	private static final String CONNECT_REGEX = " %s ";
	private static final String SPLIT_CONNECT_REGEX_PATTERN = "\\s+%s\\s+";
	private static final String OPEN_PARENTHESES = "(";
	private static final String CLOSE_PARENTHESES = ")";
	private static final String OPEN_PARENTHESES_REGEX = "\\(";
	private static final String CLOSE_PARENTHESES_REGEX = "\\)";
	private static final String STRING_PARAM_REGEX = "%s";

	private static final String[] CONNECT_CONDITIONS = { AND, OR };
	private static final String[] COMPARE_CONDITIONS = { EQ, NE, LIKE };

	/**
	 *
	 * This method try to make all filter condition in tolower keyword Except: Date
	 * filter - Number filter
	 * 
	 * @param filter of IPeople1.0
	 * @return a new filter without case sensitivity
	 */
	public static String removeCaseSensitivityForFilter(String filter) {
		var output = splitFilterByConnectRegex(filter, EMPTY, CONNECT_CONDITIONS);
		return removePlusAndWhiteSpaces(output);
	}

	/**
	 * Split filter by {@link #CONNECT_CONDITIONS} items Then call to
	 * {@link #splitFilterByCompareRegex(String, String, String...)} method to unify
	 * operator
	 * 
	 * @param value
	 * @param filters
	 * @param regex
	 * @return filter
	 */
	private static String splitFilterByConnectRegex(String value, String filters, String... regex) {
		var regexLength = regex.length;
		final String connectRegex = regex[0];
		String[] filterArrays = value.split(format(SPLIT_CONNECT_REGEX_PATTERN, connectRegex));
		List<String> connectFilters = new ArrayList<>();
		for (var condition : filterArrays) {
			if (StringUtils.containsAny(condition, CONNECT_CONDITIONS) && regexLength > 1) {
				var compileFilter = compileFilterEndWithRegex(filters, connectRegex, connectFilters);
				var output = splitFilterByConnectRegex(condition, compileFilter, moveToNextIndex(regexLength, regex));
				connectFilters.clear();
				connectFilters.add(output);
			} else {
				condition = splitFilterByCompareRegex(condition, EMPTY, COMPARE_CONDITIONS);
				connectFilters.add(condition);
			}
		}
		return compileFilter(connectRegex, connectFilters, filters);
	}

	/**
	 * Split filter by {@value #COMPARE_CONDITIONS} items Then put the query to
	 * tolower syntax if needed
	 * 
	 * @param condition
	 * @param statement
	 * @param regex
	 * @return filter inside the tolower syntax
	 */
	private static String splitFilterByCompareRegex(String condition, String statement, String... regex) {
		var regexLength = regex.length;
		final String compareRegex = regex[0];
		final boolean shouldNotLowerCase = shouldNotLowerCaseCondition(condition);
		String[] filterArrays = condition.split(format(SPLIT_CONNECT_REGEX_PATTERN, compareRegex));
		var outStatement = new ArrayList<String>();
		for (int processIndex = 0; processIndex < filterArrays.length; processIndex++) {
			var element = filterArrays[processIndex].trim();
			if (shouldNotLowerCase) {
				outStatement.add(removePlusAndWhiteSpaces(element).trim());
			} else {
				if (StringUtils.containsAny(element, COMPARE_CONDITIONS) && regexLength > 1) {
					var compileStatement = compileFilterEndWithRegex(statement, compareRegex, outStatement);
					var outCondition = splitFilterByCompareRegex(element, compileStatement,
							moveToNextIndex(regexLength, regex));
					outStatement.clear();
					outStatement.add(outCondition);
				} else {
					if (compareRegex.equals(LIKE) && processIndex > 0) {
						outStatement.add(filterArrays[1].toLowerCase().trim());
						continue;
					}
					var lowercaseStatement = format(IPeopleConstants.LOWER_CASE_FILTER,
							removePlusAndWhiteSpaces(element).trim());
					// In case element has open or close parentheses
					// Then we remove it before tolowercase
					// And merge to original format afterwards
					if (element.startsWith(OPEN_PARENTHESES) || element.endsWith(CLOSE_PARENTHESES)) {
						var valueOnly = element.replaceAll(OPEN_PARENTHESES_REGEX, EMPTY)
								.replaceAll(CLOSE_PARENTHESES_REGEX, EMPTY);
						var backupElementFormat = element.replace(valueOnly, STRING_PARAM_REGEX);
						lowercaseStatement = format(IPeopleConstants.LOWER_CASE_FILTER,
								removePlusAndWhiteSpaces(valueOnly).trim());
						lowercaseStatement = format(backupElementFormat, lowercaseStatement);
					}
					outStatement.add(lowercaseStatement);
				}
			}
		}
		return compileFilter(compareRegex, outStatement, statement);
	}

	/**
	 * This method similar {@link #compileFilter(String, List, String)} method. But
	 * it appends the regex to the end of statement to make filters are connected
	 * 
	 * @param filters
	 * @param connectRegex
	 * @param connectFilters
	 * @return filter
	 */
	private static String compileFilterEndWithRegex(String filters, final String connectRegex,
			List<String> connectFilters) {
		var compileFilter = compileFilter(connectRegex, connectFilters, filters);
		if (StringUtils.isNoneBlank(compileFilter)) {
			compileFilter = join(SPACE, compileFilter, connectRegex);
		}
		return compileFilter;
	}

	/**
	 * Copy the array by moving to next index
	 * 
	 * @param regexLength
	 * @param regex
	 * @return new arrays
	 */
	private static String[] moveToNextIndex(int regexLength, String... regex) {
		return Arrays.copyOfRange(regex, 1, regexLength);
	}

	/**
	 * Compile filters by given regex, then appends it to origin filter
	 * 
	 * @param regex
	 * @param filterArrays list of filter conditions
	 * @param originFilter previous filter
	 * @return final filter
	 */
	private static String compileFilter(final String regex, List<String> filterArrays, String originFilter) {
		var compileFilter = EMPTY;
		if (CollectionUtils.isNotEmpty(filterArrays)) {
			filterArrays.removeIf(filter -> StringUtils.isBlank(filter));
			var regexSymbol = format(CONNECT_REGEX, regex);
			compileFilter = filterArrays.size() == 1 ? filterArrays.get(0) : join(regexSymbol, filterArrays);
		}
		return StringUtils.isBlank(originFilter) ? compileFilter : join(SPACE, originFilter, compileFilter);
	}

	/**
	 * Should not modify filter if it's comparing a date or number or using operator
	 * such as: gt, lt. Also ignore the filter if it already inside a substringof or
	 * tolower method.
	 * 
	 * @param condition
	 * @return true or false
	 */
	private static boolean shouldNotLowerCaseCondition(String condition) {
		Pattern pattern = Pattern.compile(IGNORE_REGEX);
		Matcher matcher = pattern.matcher(condition);
		return matcher.find() || condition.replaceAll(PERCENT, EMPTY).matches(NUMBER_REGEX);
	}

	/**
	 * Remove plus(+) and multi white-spaces
	 * 
	 * @param condition a filter text
	 * @return a string without plus or white-spaces
	 */
	private static String removePlusAndWhiteSpaces(String condition) {
		return condition.replaceAll(PLUS_AND_WHITE_SPACE_REGEX, SPACE);
	}
}
