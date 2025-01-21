package com.axonivy.utils.gdpr.constant;

import java.util.Arrays;
import java.util.List;

public class IPeopleConstants {
	public static final String NAME_AND_CODE_FORMAT = "%s (%s)";
	public static final String NAVIGATION_SEPARATOR = "/";
	public static final String EQUAL = " eq ";
	public static final String AND = " and ";
	public static final String OR = " or ";
	public static final String EQUAL_FILTER_PATTERN = "%s eq '%s'";
	public static final String NOT_EQUAL_FILTER_PATTERN = "%s ne '%s'";
	public static final String EQUAL_LOWER_FILTER_PATTERN = "tolower(%s) eq tolower('%s')";
	public static final String LIKE_LOWER_FILTER_PATTERN = "tolower(%s) like tolower('%%25%s%%25')";
	public static final String ACTIVE_FILTER = "status eq 'A'";
	public static final String LIKE = " like '%%25%s%%25'";
	public static final String LIKE_LOWER = " like tolower('%%25%s%%25')";
	public static final String ACTIVE_FILTER_AND = ACTIVE_FILTER + AND;
	public static final String LOWER_CASE_FILTER = "tolower(%s)";
	public static final String LOWER_CASE_FOR_LIKE_FILTER = "tolower('%%25%s%%25')";
	public static final String SUBSTRING_FILTER_PATTERN = "substringof('%s', %s)";
	public static final String AT = "@";
	public static final String EMPLOYMENT_ACTIVE_STATUS = "t";
	public static final String SUBSTRING_LOWER_FILTER_PATTERN = "substringof(tolower('%s'), tolower(%s))";
	public static final String INSIDE_PARENTHESIS_PATTERN = "(%s)";
	public static final String REQUEST_BODY_PARAM = "requestBody";
	public static final String UPSERT_RESULT = "upsertResult";
	public static final String ACTIVE_STATUS = "A";
	public static final String INACTIVE_STATUS = "I";
	public static final String EPOCH_DATE_FORMAT = "/Date(%s)/";
	public static final String UPSERT_START_NAME = "upsert(Object)";
	public static final String FILTER_IN = " in '";
	public static final String DATE_GREATER_PATTERN = "%s gt datetime'%s'";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String RES_LEGAL_ENTITY_ID = "0400";
	public static final String RESIPM_LEGAL_ENTITY_ID = "0403";
	public static final String RESITS_LEGAL_ENTITY_ID = "0401";
	public static final String RPT_LEGAL_ENTITY_ID = "1900";
	public static final String RPTTS_LEGAL_ENTITY_ID = "1901";
	public static final String DEFAULT_INVALID_USER_ID = "-1";
	public static final String HRBP_RELATIONSHIP_CODE = "126308";
	public static final String OPTION_YES__EXT_CODE_FROM_YES_NO_PICKLIST = "144065";
	public static final String OPTION_NO__EXT_CODE_FROM_YES_NO_PICKLIST = "144064";
	public static final String OPTION_YES__EXT_CODE_FROM_YES_NO_LOWER_CASE_PICKLIST = "127399";
	public static final String OPTION_NO__EXT_CODE_FROM_YES_NO_LOWER_CASE_PICKLIST = "127400";
	public static final String MDF_ENUM_VALUE_FILTER_PATTERN = "key eq '%s'";
	public static final String MESSAGE_START_KEYWORD = "message:";
	public static final String RECORD_DETAIL_START_KEYWORD = "Failed record info:";
	public static final String PICKLIST_WITH_LOCALE = "picklist(%s)_locale(%s)";
	public static final String FO_OBJECT_WITH_LOCALE = "foObject(%s)_locale(%s)";
	public static final String LOCALE_SEPARATION = "___";
	public static final String REQUEST_FAILED_STATUS = "F";
	public static final String REQUEST_SUCCEED_STATUS = "S";
	public static final String COMMA = ",";

	public static final List<String> SPAIN_GROUP_LEGAL_ENTITIES = Arrays.asList(
		RES_LEGAL_ENTITY_ID,
		RESIPM_LEGAL_ENTITY_ID,
		RESITS_LEGAL_ENTITY_ID
	);
	public static final List<String> PORTUGAL_GROUP_LEGAL_ENTITIES = Arrays.asList(
		RPT_LEGAL_ENTITY_ID,
		RPTTS_LEGAL_ENTITY_ID
	);
}
