package com.jxwz.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
	
	public static String createCode() {
		String codeStr = "1234567890";
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<4;i++) {
			int index = rand.nextInt(codeStr.length());
			sb.append(codeStr.charAt(index));
		}
		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(formatDate(new Date(),COMMON_FORMAT));
//	}
}
