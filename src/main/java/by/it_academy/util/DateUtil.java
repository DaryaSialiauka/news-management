package by.it_academy.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

	public static Date localdateToSQLDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}
	
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static String localdateToStr(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return formatter.format(date);
	}
	
}
