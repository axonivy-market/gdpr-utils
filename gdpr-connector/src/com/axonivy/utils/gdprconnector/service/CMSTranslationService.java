package com.axonivy.utils.gdprconnector.service;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.axonivy.utils.gdprconnector.enums.LegalEntityEnum;

import ch.ivyteam.ivy.environment.Ivy;

public class CMSTranslationService {
	private static final String CMS_EMAIL_FOOTER = "/Mails/RicohEmailTemplate/footer";
	private static final String CMS_EMAIL_FOOTER_LOGO = "/Images/Branding/logo";
	private static final String DELIMITER_NEW_LINE = "<br/>";
	private static final String DELIMITER_SLASH = "/";
	private static final String TABLE_ROW_OPEN_TAG = "<tr>";
	private static final String TABLE_ROW_CLOSE_TAG = """
			</tr>
			""";
	private static final String TABLE_OPEN_TAG = "<table>";
	private static final String TABLE_CLOSE_TAG = "</table>";
	private static final String TABLE_CELL_ELEMENT = "td";
	private static final String TABLE_ROW_ELEMENT = "tr";
	private static final String CMS_PATTERN = "&lt;%=ivy\\.cms\\.co\\(\"([^\"]+)\"\\)%&gt;";

	private CMSTranslationService() {
	}

	public static String getTranslateStringFromCmsByLegalEntityWithSlash(String cmsURI, LegalEntityEnum legalEntity,
			Object... objects) {
		return getTranslateStringFromCmsByLegalEntity(cmsURI, legalEntity, DELIMITER_SLASH, objects);
	}

	/**
	 * Private helper method to retrieve a translated string from the Content
	 * Management System (CMS) based on the specified URI, legal entity, delimiter,
	 * and additional parameters.
	 *
	 * @param cmsURI      The URI of the content in the CMS.
	 * @param legalEntity The legal entity for which the translation is requested.
	 * @param delimiter   The delimiter used to join multiple translations (e.g.,
	 *                    new line or forward slash).
	 * @param objects     Additional parameters to be used in the translation.
	 * @return The translated string for the specified legal entity, using the
	 *         specified delimiter.
	 */
	public static String getTranslateStringFromCmsByLegalEntity(String cmsURI, LegalEntityEnum legalEntity,
			String delimiter, Object... objects) {
		if (Objects.isNull(legalEntity) || CollectionUtils.isEmpty(legalEntity.getDefaultLanguages())) {
			return IvyService.getTranslatedCmsByLocale(cmsURI, Ivy.session().getContentLocale(), objects);
		}

		return legalEntity.getDefaultLanguages().stream().map(language -> {
			return IvyService.getTranslatedCmsByLocale(cmsURI, language, objects);
		}).collect(Collectors.joining(delimiter));
	}

	public static String getTranslatedMailContentAndFooterFromCmsByLegalEntity(String cmsURI, boolean hasCmsInParams,
			LegalEntityEnum legalEntity, Object... objects) {
		if (Objects.isNull(legalEntity) || CollectionUtils.isEmpty(legalEntity.getDefaultLanguages())) {
			Locale sessionLocale = Ivy.session().getContentLocale();
			return translateEmailBodyAndFooterByLanguage(legalEntity, cmsURI, sessionLocale, hasCmsInParams, objects);
		}

		return legalEntity.getDefaultLanguages().stream().map(language -> {
			return translateEmailBodyAndFooterByLanguage(legalEntity, cmsURI, language, hasCmsInParams, objects);
		}).collect(Collectors.joining(DELIMITER_NEW_LINE));
	}

	private static String translateEmailBodyAndFooterByLanguage(LegalEntityEnum legalEntity, String cmsURI,
			Locale language, boolean hasCmsInParams, Object... objects) {
		Object[] translatedTableCells = translateCmsFromTableBody(objects, language);
		if (hasCmsInParams) {
			translatedTableCells = IvyService.getTranslatedCmsObjectsByLocale(language, translatedTableCells);
		}

		String body = IvyService.getTranslatedCmsByLocale(cmsURI, language, translatedTableCells);
		String footer = IvyService.getTranslatedCmsByLocale(CMS_EMAIL_FOOTER, language,
				Ivy.cm().ref(CMS_EMAIL_FOOTER_LOGO));
		return body + footer;
	}

	private static Object handleTableContent(Object data, Locale language) {
		if (isTableBody(data)) {
			String table = TABLE_OPEN_TAG + data + TABLE_CLOSE_TAG;
			Document doc = Jsoup.parse(table);
			handleMacroCmsInCells(language, doc);
			data = doc.select(TABLE_ROW_ELEMENT);
		}
		return data;
	}

	private static void handleMacroCmsInCells(Locale language, Document doc) {
		Elements tableCells = doc.select(TABLE_CELL_ELEMENT);
		Pattern regexPattern = Pattern.compile(CMS_PATTERN);
		for (Element cell : tableCells) {
			for (TextNode node : cell.textNodes()) {
				translateCmsInText(language, regexPattern, node);
			}
		}
	}

	private static void translateCmsInText(Locale language, Pattern regexPattern, TextNode node) {
		String text = node.toString();
		Matcher matcher = regexPattern.matcher(text);
		var foundCMSPattern = false;
		StringBuffer translatedText = new StringBuffer();
		while (matcher.find()) {
			foundCMSPattern = true;
			String match = matcher.group(1);
			String translatedCms = IvyService.getTranslatedCmsByLocale(match, language);
			matcher.appendReplacement(translatedText,translatedCms);
		}
		if (foundCMSPattern) {
			matcher.appendTail(translatedText);
			node.text(translatedText.toString());
		}
	}

	private static Object[] translateCmsFromTableBody(Object[] params, Locale language) {
		if (params == null) {
			return new Object[0];
		}

		Object[] result = new Object[params.length];
		if (params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				result[i] = handleTableContent(params[i], language);
			}
		}
		return result;
	}

	private static Boolean isTableBody(Object data) {
		return Objects.nonNull(data)
				? data.toString().startsWith(TABLE_ROW_OPEN_TAG) && data.toString().endsWith(TABLE_ROW_CLOSE_TAG)
						: false;
	}
}
