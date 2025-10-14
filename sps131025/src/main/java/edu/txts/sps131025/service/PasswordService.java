package edu.txts.sps131025.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PasswordService {

	private final PasswordEncoder passwordEncoder;

	public PasswordService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public String encode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	// Kiểm tra strength của BCrypt
	public void checkBcryptStrength(String encodedPassword) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		int strength = extractBcryptStrength(encodedPassword);
		log.info("BCrypt strength: {}", strength);
	}

	private int extractBcryptStrength(String encodedPassword) {
		// BCrypt encoded password format: $2a$12$...
		if (encodedPassword.startsWith("$2a$")) {
			String strengthStr = encodedPassword.substring(4, 6);
			return Integer.parseInt(strengthStr);
		}
		return -1;
	}
}
