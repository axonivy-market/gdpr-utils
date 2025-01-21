package com.axonivy.utils.gdpr.constant;

import java.util.Locale;

public class Constants {
	public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	public static final String ERROR_BASE = "com:ricoh:hr:";
	public static final String ERROR_CORE = ERROR_BASE + "core:";
	public static final String SIGNAL_BASE = "com:ricoh:hr:";
	public static final String SIGNAL_CORE = ERROR_BASE + "core:";
	public static final String EMPLOYEE_NAME_PATTERN = "%s %s";
	public static final String FILE_NAME_PATTERN = "%s %s";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String HYPHEN = "-";
	public static final String UNDERSCORES = "_";
	public static final String SHOW_DIALOG_SCRIPT = "PF('%s').show()";
	public static final String CLOSE_DIALOG_SCRIPT = "PF('%s').hide()";
	public static final String ONLY_NUMBER_REGEX = "[0-9]+";
	public static final String WINDOWS_FILE_NAMING_REGEX = "[^a-zA-Z0-9.-]";
	public static final String MORE_ONE_HYPHEN_REGEX = "-+";
	public static final String NAME_AND_CODE_FORMAT = "%s (%s)";
	public static final int BATCH_SIZE = 1000;
	public static final String COMPANY_NAME = "companyName";
	public static final String VALUE_INSIDE_PARENTHESES_REGEX = "\\((.*?)\\)";
	public static final String REQUEST_ID = "requestId";
	public static final String CASE_ID = "caseId";
	public static final String ID = "id";
	public static final String PREHIRE_PERSONAL_INFORMATION_ID = "preHirePersonalInformationId";
	public static final String YES = "YES";
	public static final String YEAR = "YEAR";
	public static final String MONTH = "MONTH";
	public static final String WEEK = "WEEK";
	public static final String DAY = "DAY";
	public static final String HOUR = "HOUR";
	public static final String HASH = "#";
	public static final String COMMA = ",";
	public static final String SEMICOLON = ";";
	
	// dev option variable names
	public static final String DEV_OPTION_ENABLE_FIXED_EMAIL_VARIABLE = "restrictedEntity.developerOptions.enableFixedEmail";
	public static final String DEV_OPTION_FIXED_EMAIL_VARIABLE = "restrictedEntity.developerOptions.fixedEmail";
	public static final String DEV_OPTION_USE_EMAIL_INSTEAD_OF_USERNAME_VARIABLE = "restrictedEntity.useEmailInsteadOfUsername";
	public static final String DEV_OPTION_DISABLED_ADOBE_ESIGN = "restrictedEntity.developerOptions.disabledAdobeEsign";
	// Max width of A4 page is 595px
	public static final int MAX_WIDTH_OF_A4_PAGE = 595;
	// Max height of A4 page is 842px
	public static final int MAX_HEIGHT_OF_A4_PAGE = 842;
	// used for steps : use for default process if there is no reject.
	public static final String DEFAULT_PROCESS_CHAIN = "DEFAULT_PROCESS_CHAIN";
	// used for steps : Budget holder approval, HR BP approval when cancel/reject
	// the request.
	public static final String CANCEL_BY_BUDGET_HOLDER_PROCESS_CHAIN = "CANCEL_BY_BUDGET_HOLDER_PROCESS_CHAIN";
	// used for steps :Review and create document when cancel/reject the request.
	public static final String CANCEL_AT_DOCUMENTATION_PROCESS_CHAIN = "CANCEL_AT_DOCUMENTATION_PROCESS_CHAIN";
	// used for when check box of "employee signature necessary" is ACTIVE and at
	// the step Review and create document
	public static final String FOUR_EYE_PRINCIPLE_PROCESS_CHAIN = "FOUR_EYE_PRINCIPLE_PROCESS_CHAIN";
	// used for when check box of "employee signature necessary" is not active
	public static final String NO_EMPLOYEE_SIGNATURE_PROCESS_CHAIN = "NO_EMPLOYEE_SIGNATURE_PROCESS_CHAIN";
	public static final String FOUR_EYE_PRINCIPLE_SEND_FINAL_DOCUMENT_PROCESS_STEPS = "FOUR_EYE_PRINCIPLE_SEND_FINAL_DOCUMENT_PROCESS_STEPS";
	public static final String NO_EMPLOYEE_SIGNATURE_SEND_FINAL_DOCUMENT_PROCESS_STEPS = "NO_EMPLOYEE_SIGNATURE_SEND_FINAL_DOCUMENT_PROCESS_STEPS";
}
