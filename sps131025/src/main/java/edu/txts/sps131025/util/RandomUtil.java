package edu.txts.sps131025.util;

package com.example.demo.util;

import java.security.SecureRandom;

public class RandomUtil {

	private static final String DIGITS = "0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	public static String generateOtp(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
		}
		return sb.toString();
	}
}

