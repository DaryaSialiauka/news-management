package by.it_academy.util;

import java.util.Calendar;
import java.sql.Date;

public final class DateAndCalendar {

	public static Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date calendarToDate(Calendar calendar) {
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
}
