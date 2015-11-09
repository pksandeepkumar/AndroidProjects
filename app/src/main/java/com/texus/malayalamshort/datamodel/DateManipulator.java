/**
 * 
 */
package com.texus.malayalamshort.datamodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 *
 */
public class DateManipulator {
	
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public long timeInMillisecond;
	public Calendar calendar;
	
	/**
	 * 
	 */
	public DateManipulator(long timeInMillisecond) {
		this.timeInMillisecond = timeInMillisecond;
		this.calendar = Calendar.getInstance();
		this.calendar.setTimeInMillis(timeInMillisecond);
	}
	
	public DateManipulator( Calendar calendar) {
		this.calendar = calendar;
		this.timeInMillisecond = calendar.getTimeInMillis();
	}
	
	public DateManipulator( String dateString) {
		this.timeInMillisecond = getDateTime(dateString);
		this.calendar = Calendar.getInstance();
		this.calendar.setTimeInMillis(timeInMillisecond);
	}
	
	public static String setPadding(int num) {

		return (num < 10) ? "0" + num : "" + num;
	}
	
	private String getAmPmString() {
		String am_pm_string = (calendar.get(Calendar.AM_PM) == Calendar.AM) ? "am" : "pm";
		return am_pm_string;
	}
	
	public String getDateString() {
		return getMonthName() + " " + getDateOfMonth() + " at " + getTime();
	}
	/**
	 * 
	 * @return DD-MM-YYYY
	 */
	public String getDateStringInDOB() {
		return setPadding(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + 
				setPadding(calendar.get(Calendar.MONTH) + 1) + "-" +
				calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * @return YYYY-MM-DD
	 */
	public String getDateStringInYYYYMMDD() {
		return calendar.get(Calendar.YEAR) + "-" 
				+ setPadding(calendar.get(Calendar.MONTH) + 1) + "-"
				+ setPadding(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 
	 * @return YYYYMMDDHHMMSS
	 */
	public String getDateStringInYYYYMMDDHHMMSS() {
		return calendar.get(Calendar.YEAR)  
				+ setPadding(calendar.get(Calendar.MONTH) + 1) + ""
				+ setPadding(calendar.get(Calendar.DAY_OF_MONTH))
				+ setPadding(calendar.get(Calendar.HOUR_OF_DAY))
				+ setPadding(calendar.get(Calendar.MINUTE))
				+ setPadding(calendar.get(Calendar.SECOND));
	}
	
	
	/**
	 * HH:MM am DD-MM-YYYY
	 * @return
	 */
	public String getDateTimeString() {
		String dateTimeString = "";
		dateTimeString = getTime() + " " + getDateStringInDOB();
		return dateTimeString;
	}
	
	
	private String getTime() {
		String hour = setPadding(calendar.get(Calendar.HOUR));
		String minute = setPadding(calendar.get(Calendar.MINUTE));
		return hour + ":" + minute + " " + getAmPmString();
	}
	
	private String getDateOfMonth() {
		return setPadding(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	private String getMonthName() {
		
		int month = calendar.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
			return "Jan";
		case Calendar.FEBRUARY:
			return "Feb";
		case Calendar.MARCH:
			return "Mar";
		case Calendar.APRIL:
			return "Apr";
		case Calendar.MAY:
			return "May";
		case Calendar.JUNE:
			return "Jun";
		case Calendar.JULY:
			return "Jul";
		case Calendar.AUGUST:
			return "Aug";
		case Calendar.SEPTEMBER:
			return "Sep";
		case Calendar.OCTOBER:
			return "Oct";
		case Calendar.NOVEMBER:
			return "Nov";
		case Calendar.DECEMBER:
			return "Dec";
		}

		return "Jan";
	}
	
	
	public long getDateTime(String dateString) {
	    SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT);
	    java.util.Date date = null;
	    Calendar calendar;

	    try {
	        date = form.parse( dateString );
	        calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    return calendar.getTimeInMillis();
	    } catch (java.text.ParseException e) {
	        e.printStackTrace();
	    }
	    
	    if( date == null) {
	    	return Calendar.getInstance().getTimeInMillis();
	    }

	    return 0;
	}
	
	public String getDateStringInDDMMYYYY() {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return setPadding(day) + "-" + setPadding((month + 1))
				+ "-" + year;
	}
	
//	private String getDayName(int day) {
//
//		switch (day) {
//		case Calendar.SUNDAY:
//			return getString(R.string.Sunday);
//		case Calendar.MONDAY:
//			return getString(R.string.Monday);
//		case Calendar.TUESDAY:
//			return getString(R.string.Tuseday);
//		case Calendar.WEDNESDAY:
//			return getString(R.string.Wednesday);
//		case Calendar.THURSDAY:
//			return getString(R.string.Thursday);
//		case Calendar.FRIDAY:
//			return getString(R.string.Friday);
//		case Calendar.SATURDAY:
//			return getString(R.string.Saturday);
//		}
//
//		return getString(R.string.Sunday);
//	}

}
