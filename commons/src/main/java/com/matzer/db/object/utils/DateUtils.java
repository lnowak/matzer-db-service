package com.matzer.db.object.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * Utilities class for dates.
 * 
 * @author lkawon@gmail.com
 *
 */
public final class DateUtils {

	/**
	 * Hidden constructor.
	 */
	private DateUtils() {
		
	}
	
	/**
	 * This method checks if Date is instance of Timestamp and converts it to Date.
	 * 
	 * @param date		date to normalize
	 * @return			normalized date
	 */
	public static Date normalize(Date date) {
		Date tempDate = null;
		if (date instanceof Timestamp) {
			tempDate = new Date(date.getTime());
		} else {
			tempDate = date;
		}
		
		return tempDate;
	}
}
