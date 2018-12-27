package com.hush.panduan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class panduan {
	
	

	/*
	 * 联系方式
	 */
	public static boolean isMobileNum(String mobile) {
		if (mobile.length() != 11) {
			return false;
		} else {
			/**
			 * 移动号段正则表达式
			 */
			String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
			/**
			 * 联通号段正则表达式
			 */
			String pat2 = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
			/**
			 * 电信号段正则表达式
			 */
			String pat3 = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
			/**
			 * 虚拟运营商正则表达式
			 */
			String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

			Pattern pattern1 = Pattern.compile(pat1);
			Matcher match1 = pattern1.matcher(mobile);
			boolean isMatch1 = match1.matches();
			if (isMatch1) {
				return true;
			}
			Pattern pattern2 = Pattern.compile(pat2);
			Matcher match2 = pattern2.matcher(mobile);
			boolean isMatch2 = match2.matches();
			if (isMatch2) {
				return true;
			}
			Pattern pattern3 = Pattern.compile(pat3);
			Matcher match3 = pattern3.matcher(mobile);
			boolean isMatch3 = match3.matches();
			if (isMatch3) {
				return true;
			}
			Pattern pattern4 = Pattern.compile(pat4);
			Matcher match4 = pattern4.matcher(mobile);
			boolean isMatch4 = match4.matches();
			if (isMatch4) {
				return true;
			}
			return false;
		}
	}

	/*
	 * 身份证
	 */
	public static boolean validate(String no) {
		// 对身份证号进行长度等简单判断
		if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]")) {
			return false;
		}
		// 1-17位相乘因子数组
		int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		// 18位随机码数组
		char[] random = "10X98765432".toCharArray();
		// 计算1-17位与相应因子乘积之和
		int total = 0;
		for (int i = 0; i < 17; i++) {
			total += Character.getNumericValue(no.charAt(i)) * factor[i];
		}
		// 判断随机码是否相等
		return random[total % 11] == no.charAt(17);
	}
	
	
	
	
	public static boolean isCarNo(String carNo){
		Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(?:(?![A-Z]{4})[A-Z0-9]){4}[A-Z0-9挂学警港澳]{1}$");
		Matcher m = p.matcher(carNo);
		if (!m.matches()){
			return false;
		}
			return true;
		}
}
