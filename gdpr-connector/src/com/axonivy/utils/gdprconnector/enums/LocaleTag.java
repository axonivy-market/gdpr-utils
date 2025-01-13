package com.axonivy.utils.gdprconnector.enums;

public enum LocaleTag {
	FI_FI("fi_FI", "fi"), SV_SE("sv_SE", "sv"), NL_NL("nl_NL", "nl"), DA_DK("da_DK", "da"), DE_CH("de_CH", "de"),
	ES_ES("es_ES", "es"),
	PL_PL("pl_PL", "pl"),
	PT_PT("pt_PT", "pt"), IT_IT("it_IT", "it"), FR_FR("fr_FR", "fr"), DE_DE("de_DE", "de"), EN_GB("en_GB", "en"),
	NB_NO("nb_NO", "no"),
	HU_HU("hu_HU", "hu"),
	CS_CZ("cs_CZ", "cs"), SK_SK("sk_SK", "sk"),
	DEFAULT_IVY_LOCALE("en_GB", "en_US");

	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	LocaleTag(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static LocaleTag fromName(String value) {
		for (LocaleTag locale : LocaleTag.values()) {
			if (locale.getName().equalsIgnoreCase(value)) {
				return locale;
			}
		}
		throw new IllegalArgumentException("Invalid value for Locale: " + value);
	}

	public static LocaleTag fromCode(String value) {
		for (LocaleTag locale : LocaleTag.values()) {
			if (locale.getCode().equalsIgnoreCase(value)) {
				return locale;
			}
		}
		throw new IllegalArgumentException("Invalid value for Locale: " + value);
	}
}
