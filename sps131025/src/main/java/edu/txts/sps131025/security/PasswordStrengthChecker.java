package edu.txts.sps131025.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@Slf4j
public class PasswordStrengthChecker {

	private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
	private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
	private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
	private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");

	public static PasswordStrength checkStrength(String password) {
		if (password == null || password.length() < 8) {
			return PasswordStrength.WEAK;
		}

		int score = 0;

		// Length check
		if (password.length() >= 12) score += 2;
		else if (password.length() >= 8) score += 1;

		// Character variety checks
		if (UPPERCASE_PATTERN.matcher(password).find()) score++;
		if (LOWERCASE_PATTERN.matcher(password).find()) score++;
		if (DIGIT_PATTERN.matcher(password).find()) score++;
		if (SPECIAL_CHAR_PATTERN.matcher(password).find()) score++;

		// Entropy check (simple version)
		if (calculateEntropy(password) > 3.5) score++;

		if (score >= 6) return PasswordStrength.STRONG;
		if (score >= 4) return PasswordStrength.MEDIUM;
		return PasswordStrength.WEAK;
	}

	private static double calculateEntropy(String password) {
		Map<Character, Integer> charCounts = new HashMap<>();
		for (char c : password.toCharArray()) {
			charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
		}

		double entropy = 0.0;
		int length = password.length();

		for (int count : charCounts.values()) {
			double probability = (double) count / length;
			entropy -= probability * (Math.log(probability) / Math.log(2));
		}

		return entropy;
	}

	public enum PasswordStrength {
		WEAK, MEDIUM, STRONG
	}
}
