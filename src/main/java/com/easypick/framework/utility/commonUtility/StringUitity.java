package com.easypick.framework.utility.commonUtility;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.exception.ExceptionList;
import com.easypick.framework.utility.vo.Page;

public class StringUitity {

	public static final String METHOD_TYPE_FIRST = "FIRST";
	public static final String METHOD_TYPE_END = "END";

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getString(String key, Integer N, String type) {
		if (type.equalsIgnoreCase(METHOD_TYPE_FIRST)) {
			return key.substring(0, N);
		} else {

		}

		return null;

	}

	public static String replaceString(String key, String replacekey) {

		return key.replaceAll(replacekey, "");

	}

	public static Page validatePageNumber(String key, int N, String type) throws BussinessException {

		key = replaceString(key, "page-");
		String salt = getString(key, N, type);
		if (Objects.isNull(salt)) {
			throw new BussinessException(ExceptionList.INVALID_PAGE);
		} else {
			Page page = new Page();
			// generateRandomNumber(1111,9999)
			SecurityEncription dec = new SecurityEncription(salt);
			try {
				String encs = dec.encrypt("1");
				String enc = dec.decrypt(key.toString().trim());
				page.setCurrentPage(Integer.parseInt(enc));
				page.setPages(true);
				return page;

			} catch (Exception e) {
				throw new BussinessException(ExceptionList.INVALID_PAGE);
			}

		}

	}

	public static String generateRandomNumber(int maximum, int minimum) {
		Random rn = new Random();
		int range = maximum - minimum + 1;
		Integer randomNum = rn.nextInt(range) + minimum;
		return randomNum.toString();

	}

	public static String generateUploadPath() {
		LocalDate currentDate = LocalDate.now(); // 2016-06-17
		Month month = currentDate.getMonth(); // JUNE
		int year = currentDate.getYear(); // 2016
		return (year + "-" + month).toLowerCase();
	}

	public static String convertToSmallLetter(String str) {
		String cap = str.substring(0, 1).toLowerCase() + str.substring(1);
		return cap;
	}

	public static String filterNumber(String string) {
		String[] tag = string.split(",");
		StringBuilder value = new StringBuilder();
		for (String num : tag) {
			try {
				value.append(Integer.parseInt(num) + ",");
			} catch (Exception e) {
			}
		}
		String key = value.toString().replaceAll(",$", "");
		return key;
	}

	public static String filterNotNumber(String string) {
		String[] tag = string.split(",");
		StringBuilder value = new StringBuilder();
		for (String num : tag) {
			try {
				int val = Integer.parseInt(num);
			} catch (Exception e) {

				value.append("'" + num + "',");
			}
		}
		String key = value.toString().replaceAll(",$", "");
		if (Objects.isNull(key) || key.trim().length() == 0) {
			key = "''";
		}
		return key;
	}

	public static String getTag(String tag2) {
		String[] d1 = tag2.split(",");
		StringBuilder data = new StringBuilder();
		for (String d : d1) {
			data.append("#" + d + "#,");
		}
		return data.toString().replaceAll(",$", "");
	}

	public static String convertToTags(String string) {
		String tags = "";
		if (Objects.nonNull(string)) {
			String[] stringTag = string.split(",");

			for (String tag : stringTag) {
				tags = "#" + tag + "#," + tags;
			}
		}
		return tags;
	}

	public static String removeHashTags(String tag) {
		 return tag.replace("#", "").replaceAll(",$", "");
	}
}
