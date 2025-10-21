package edu.txts.sps131025.util;


package com.example.demo.util;

import java.util.regex.Pattern;

public class ValidationUtil {

	private static final Pattern EMAIL_PATTERN =
			Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

	public static boolean isValidEmail(String email) {
		return email != null && EMAIL_PATTERN.matcher(email).matches();
	}

	public static boolean isNotEmpty(String value) {
		return value != null && !value.trim().isEmpty();
	}
}
