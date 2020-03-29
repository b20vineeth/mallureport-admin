package com.easypick.framework.utility.validation;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.vo.Page;

public class CommonValidator {
	// Email Regex java
	private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	private static final String USERNAME_REGEX = "^[a-z0-9_-]{3,15}$";
	private static final String FIRST_NAME_REGEX = "^[a-zA-Z ]{3,25}$";
	private static final String LAST_NAME_REGEX = "^[a-zA-Z ]{1,25}$";
	private static final String MOBILE_REGEX = "(0/91)?[6-9][0-9]{9}";

	// static Pattern object, since pattern is fixed .
	private static Pattern pattern;

	// non-static Matcher object because it's created from the input String
	private Matcher matcher;

	public CommonValidator() {
		// initialize the Pattern object

	}

	/**
	 * This method validates the input email address with EMAIL_REGEX pattern
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		if (Objects.nonNull(email)) {
			matcher = pattern.matcher(email);
			return matcher.matches();
		} else
			return false;
	}

	public boolean validateUsername(String username) {

		pattern = Pattern.compile(USERNAME_REGEX, Pattern.CASE_INSENSITIVE);
		if (Objects.nonNull(username)) {
			matcher = pattern.matcher(username);
			return matcher.matches();
		} else
			return false;
	}

	public boolean validateName(UserSetupVo userSetupVo, String type) {

		if (type.equals("F")) {
			pattern = Pattern.compile(FIRST_NAME_REGEX, Pattern.CASE_INSENSITIVE);
			if (Objects.nonNull(userSetupVo.getFirstName())) {
				matcher = pattern.matcher(userSetupVo.getFirstName());
				return matcher.matches();
			} else
				return false;

		} else if (type.equals("L")) {
			pattern = Pattern.compile(LAST_NAME_REGEX, Pattern.CASE_INSENSITIVE);
			if (Objects.nonNull(userSetupVo.getLastName())) {
				matcher = pattern.matcher(userSetupVo.getLastName());
				return matcher.matches();
			} else
				return false;
		}

		return false;
	}

	public boolean validateMobile(String mob) {
		pattern = Pattern.compile(MOBILE_REGEX, Pattern.CASE_INSENSITIVE);
		if (Objects.nonNull(mob)) {
			matcher = pattern.matcher(mob);
			return matcher.matches();
		} else
			return false;
	}

	public static String getlimit(Page page) {
		if (Objects.nonNull(page)) {
			Integer start = page.getCurrentPage()-1;
			 start=start*page.getPerPage();
			
			 return " limit " + start + "," + page.getPerPage();
		}

		return "";

	}

}
