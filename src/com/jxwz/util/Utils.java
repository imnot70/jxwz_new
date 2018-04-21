package com.jxwz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private static SimpleDateFormat sdf;
	public static final String COMMON_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_ONLY = "yyyy-MM-dd";
	public static final String TIME_ONLY = "HH:mm:ss";
	
	public static String formatDate(Date date,String pattern) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String formatDateCommon(Date date) {
		sdf = new SimpleDateFormat(COMMON_FORMAT);
		return sdf.format(date);
	}
	
	public static String formatCurrentDate() {
		sdf = new SimpleDateFormat(COMMON_FORMAT);
		return sdf.format(new Date());
	}
	
//	public static void main(String[] args) {
//		System.out.println(formatDate(new Date(),COMMON_FORMAT));
//	}
}
