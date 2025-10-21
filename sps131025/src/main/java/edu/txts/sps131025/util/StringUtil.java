package edu.txts.sps131025.util;

package com.example.demo.util;

public class StringUtil {

	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}
}

