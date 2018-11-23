package com.gridpoint.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static String getDateFormat(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d,YYYY hh:mm");
		String str = simpleDateFormat.format(date);
		System.out.println("The value for the Date is : :  :" + str);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d,YYYY");
		String date2 = dateFormat.format(str);
		System.out.println("The value for the Formatted date is : : : " + date2);
		return date2;
	}

	public static void main(String[] args) {
		DateUtils.getDateFormat("2014-02-12");
	}

	public static String getTodaysDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Calendar cal = Calendar.getInstance();
		Date prevoiusmonthDate = cal.getTime();
		String finalDate = df.format(prevoiusmonthDate);
		return finalDate;
	}

	public static String getStartDate(Calendar sDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(sDate.getTime());
	}

	public static String getEndDate(Calendar eDate, int addDays) {
		eDate.add(Calendar.DAY_OF_MONTH, addDays);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(eDate.getTime());
	}

	@SuppressWarnings("deprecation")
	public static String addMonthsToDate(int months, String date1) {
		Date date = new Date(date1);
		Date newdate = org.apache.commons.lang3.time.DateUtils.addMonths(date, months);
		DateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		return formatter.format(newdate);
	}

	@SuppressWarnings("deprecation")
	public static String addOrDeleteMonths(int months, String date1) {
		Date date = new Date(date1);
		Date newdate = org.apache.commons.lang3.time.DateUtils.addMonths(date, months);
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(newdate);
	}

	@SuppressWarnings("deprecation")
	public static String dateStringConversion(String accessdate) throws ParseException {
		Date date = new Date(accessdate);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String validDate = formatter.format(date);
		System.out.println(validDate);
		return validDate;
	}
}
