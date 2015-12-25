package com.land.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		return dt;
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dt = sdf.format(new Date());
		return dt;
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDate());
	}
}
