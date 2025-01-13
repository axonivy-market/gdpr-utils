package com.axonivy.utils.gdprconnector.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Duration;

@ManagedBean
@ApplicationScoped
public class DateUtils {
	private static final String DD_MM_YYYY_HH_MM = "dd.MM.yyyy HH:mm";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss";
	private static final String DD_MM_YYYY = "dd.MM.yyyy";
	public static final DateTimeFormatter DD_MM_YYYY_PATTERN = DateTimeFormatter.ofPattern(DD_MM_YYYY);
	public static final DateTimeFormatter DD_MM_YYYY_HH_MM_PATTERN = DateTimeFormatter.ofPattern(DD_MM_YYYY_HH_MM);
	public static final SimpleDateFormat DD_MM_YYYY_DATE_PATTERN = new SimpleDateFormat(DD_MM_YYYY);
	public static final String DEFAULT_DELAY_DURATION_PERIOD = "10PD";
	public static final String DD_MM_YYYY_PATTERN_WITH_SLASH = "dd/MM/yyyy";
	public static final int SECONDS_IN_A_DAY = 86400;
	public static final int SECONDS_IN_AN_HOUR = 3600;
	public static final int SECONDS_IN_A_MINUTE = 60;

	public LocalDateTime getCurrentDateTime() {

		return LocalDateTime.now();
	}

	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	public LocalDate getMaxDate() {
		return LocalDate.MAX;
	}

	public LocalDate getMinDate() {
		return LocalDate.MIN;
	}

	public LocalDate getCurrentDatePlus18M() {
		return LocalDate.now().plus(18, ChronoUnit.MONTHS);
	}

	public LocalDate getCurrentDatePlus(long amount, ChronoUnit field) {
		return LocalDate.now().plus(amount, field);
	}

	public static LocalDate getLocalDatePlusWorkDays(LocalDate date, int workDays) {
		if (date == null) {
			return null;
		}

		final ch.ivyteam.ivy.scripting.objects.Date ivyDate = new ch.ivyteam.ivy.scripting.objects.Date(
				Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return Ivy.cal().getWorkDayIn(ivyDate, workDays).toJavaDate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	/**
	 * Shortcut call for older date types
	 *
	 * @see DateUtils.isOnlyXDaysRemainingFrom
	 * @param date,    using the systemDefault zoneId
	 * @param nrOfdays
	 * @return
	 */
	public boolean isOnlyXDaysRemainingFromDate(Date date, Integer nrOfdays) {
		return isOnlyXDaysRemainingFrom(
				date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, nrOfdays);
	}

	/**
	 * Decide if only x days are remaining for specified date, using startOfDay
	 *
	 * @param date
	 * @param nrOfdays integer, if null zero is used
	 * @return true only if less than nrOfDays are remaining till specified date and
	 *         date is not null
	 */
	public boolean isOnlyXDaysRemainingFrom(LocalDate date, Integer nrOfdays) {
		final int daysCount = nrOfdays != null ? nrOfdays : 0;
		return date != null ? date.atStartOfDay().compareTo(getCurrentDateTime().plus(daysCount, ChronoUnit.DAYS)) < 1
				: false;
	}

	public static boolean isWithinDaysToTargetDate(LocalDate targetDate, Integer days) {
		final int daysCount = days != null ? days : 0;
		if (targetDate == null) {
			return false;
		}
		LocalDateTime fromDateTime = targetDate.atStartOfDay().minus(daysCount, ChronoUnit.DAYS);
		LocalDateTime toDateTime = targetDate.atStartOfDay();
		LocalDateTime today = LocalDate.now().atStartOfDay();
		return fromDateTime.compareTo(today) < 1 && toDateTime.compareTo(today) > -1;
	}

	public static String getDateHHMMStr(LocalDateTime date) {
		return date != null ? DD_MM_YYYY_HH_MM_PATTERN.format(date) : "";
	}

	public String getDateDD_MM_YYYY_HH_MM(LocalDateTime date) {
		return getDateHHMMStr(date);
	}

	public String getDateDD_MM_YYYY(java.sql.Timestamp date) {
		return getDateStr(date.toLocalDateTime().toLocalDate());

	}

	public String getDateDD_MM_YYYY(LocalDate date) {
		return getDateStr(date);
	}

	public static String getDateStr(LocalDate date) {
		return date != null ? DD_MM_YYYY_PATTERN.format(date) : "";
	}

	public static String getDateStr(Date date) {
		return date != null ? DD_MM_YYYY_DATE_PATTERN.format(date) : "";
	}

	public static Duration getIvyDurationFromNow(LocalDate future) {
		// plusMinutes(5) added, as expiry date always was like 23.04.2022 23:59 when
		// future.atStartOfDay() actually was 24.04.2022 00:00
		final long secs = LocalDateTime.now().until(future.atStartOfDay().plusMinutes(5), ChronoUnit.SECONDS);
		return new Duration(secs < 1 ? 1 : secs);// if negative duration return 1 second
	}

	public static String getFormattedDate(LocalDate date) {
		if (Objects.isNull(date)) {
			return StringUtils.EMPTY;
		}
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern());
		return formatter.format(date);
	}

	public static String getFormattedDate(Date date) {
		if (Objects.isNull(date)) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(extractPattern());
		return formatter.format(date);
	}

	private static String extractPattern() {
		String pattern = Ivy.cms().co("/Labels/DatePattern");
		if (pattern == null || pattern.isBlank()) {
			pattern = DD_MM_YYYY;
		}

		return pattern;
	}

	public static String getFormattedDateTime(LocalDateTime datetime) {
		if (Objects.isNull(datetime)) {
			return StringUtils.EMPTY;
		}
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractDateTimePattern());
		return formatter.format(datetime);
	}

	public static String getSortableFormattedDateTime(LocalDateTime datetime) {
		if (Objects.isNull(datetime)) {
			return StringUtils.EMPTY;
		}
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
		return formatter.format(datetime);
	}

	private static String extractDateTimePattern() {
		String pattern = Ivy.cms().co("/Labels/DateTimePattern");
		if (pattern == null || pattern.isBlank()) {
			pattern = DD_MM_YYYY_HH_MM;
		}

		return pattern;
	}

	public static Date getDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Duration getDurationForSurveyTask() {
		ch.ivyteam.ivy.scripting.objects.Date workDayIn = Ivy.cal().getWorkDayIn(5);
		Date expriryDate = getEndOfDate(workDayIn.toJavaDate());
		long durationBetweenDates = getSecondsBetweenDates(new Date(), expriryDate);
		return new Duration(durationBetweenDates);
	}

	public static Duration getDurationFromNowToDate(Date date) {
		Date expriryDate = getEndOfDate(date);
		long durationBetweenDates = getSecondsBetweenDates(new Date(), expriryDate);
		return new Duration(durationBetweenDates);
	}

	public static Duration getDurationFromNowToDate(LocalDate localDate) {
		LocalDateTime expriryDate = getEndOfDate(localDate);
		long durationBetweenDates = getSecondsBetweenDates(LocalDateTime.now(), expriryDate);
		return new Duration(durationBetweenDates);
	}

	public static Duration getDurationFromNowToBeginningOfDate(LocalDate localDate) {
		LocalDateTime beginningOfDate = getBeginningOfDate(localDate);
		long durationBetweenDates = getSecondsBetweenDates(LocalDateTime.now(), beginningOfDate);
		return new Duration(durationBetweenDates);
	}

	public static int getDaysBetween(LocalDate fromDate, LocalDate untilDate) {
		long secondsBetweenDates = getSecondsBetweenDates(getBeginningOfDate(untilDate), getBeginningOfDate(fromDate));
		Duration durationBetweenDates = new Duration(secondsBetweenDates);
		return durationBetweenDates.getDays();
	}

	public static Date getEndOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 30);
		return c.getTime();
	}

	public static LocalDate parseToLocalDateByDefaultPattern(String dateString) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern());
		return LocalDate.parse(dateString, formatter);
	}

	private static LocalDateTime getEndOfDate(LocalDate date) {
		return LocalDateTime.of(date, LocalTime.of(23, 59, 30));
	}

	private static LocalDateTime getBeginningOfDate(LocalDate date) {
		return LocalDateTime.of(date, LocalTime.of(0, 0, 30));
	}

	private static long getSecondsBetweenDates(Date date1, Date date2) {
		long diffInMillies = date2.getTime() - date1.getTime();
		TimeUnit unit = TimeUnit.SECONDS;
		return unit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	private static long getSecondsBetweenDates(LocalDateTime fromDate, LocalDateTime toDate) {
		return ChronoUnit.SECONDS.between(fromDate, toDate);
	}

	public static LocalDate convertMilisToLocalDate(Long milis) {
		if (milis == null || milis <= 0) {
			return null;
		}
		return Instant.ofEpochMilli(milis).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static boolean isDateAfterTodayNumberOfDays(LocalDate date, int numberOfDays) {
		LocalDate today = LocalDate.now();
		return date.isAfter(today.plusDays(numberOfDays));
	}

	public static LocalDateTime getCurrentDayMidnight() {
		return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	public static LocalDateTime getPreviousDayMidnight() {
		return getCurrentDayMidnight().minusDays(1);
	}

	public static LocalDateTime getNextDayMidnight() {
		return getCurrentDayMidnight().plusDays(1);
	}

	public static String getDocuwareDate(Date date) {
		String string = null;
		if (date != null) {
			string = String.format("/Date(%d)/", date.getTime());
		}
		return string;
	}

	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneOffset.systemDefault()).toInstant();
		return Date.from(instant);
	}

	public static Long convertLocalDateToEpochMilisecond(LocalDate date) {
		Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
		return instant.toEpochMilli();
	}

	public static String convertLocalDateToString(LocalDate date) {
		return date == null ? null : date.atStartOfDay().toString();
	}

	/**
	 * 
	 * Converts a date String to a LocalDate object. Handles null input by returning
	 * null. Uses a DateTimeFormatter with a custom pattern "dd.MM.yyyy" to parse
	 * the String and convert it to a LocalDate.
	 * 
	 * @param dateString - The input date as a String, or null
	 * @return The dateString parsed as a LocalDate, or null if input is null
	 */
	public static LocalDate convertStringToLocalDate(String dateString) {
		return dateString == null ? null : LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DD_MM_YYYY));
	}

	public static long getEpochTimeFromLocalDate(LocalDate date) {
		return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	public static String covertLocalDateToBeginingDateTimeString(LocalDate date) {
		return date == null ? null : date.atStartOfDay().toString();
	}

	public static String reformatLocalDateWithSlash(LocalDate date) {
		return DateTimeFormatter.ofPattern(DateUtils.DD_MM_YYYY_PATTERN_WITH_SLASH).format(date);
	}

	public static Boolean isDateAfterToday(LocalDate date) {
		if (Objects.isNull(date)) {
			return false;
		}
		return date.isAfter(LocalDate.now());
	}

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static String convertSecondsToDuration(long totalSeconds) {
		long days = totalSeconds / SECONDS_IN_A_DAY;
		totalSeconds %= SECONDS_IN_A_DAY;
		long hours = totalSeconds / SECONDS_IN_AN_HOUR;
		totalSeconds %= SECONDS_IN_AN_HOUR;
		long minutes = totalSeconds / SECONDS_IN_A_MINUTE;
		long seconds = totalSeconds % SECONDS_IN_A_MINUTE;
		// Format as dd-hh-mm-ss
		return String.format("%02d-%02d-%02d-%02d", days, hours, minutes, seconds);
	}
}
