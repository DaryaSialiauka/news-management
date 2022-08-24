package by.it_academy.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateAndCalendar {

	public static Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date calendarToDate(Calendar calendar) {
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static Calendar strToCalendar(String date) throws ParseException {
		if(date.isEmpty() || date == null) {
			return new GregorianCalendar();
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		cal.setTime(sdf.parse(date));
		
		return cal;
	}
	
	public static String calendarToStr(Calendar date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date.getTime());
	}
	
}
