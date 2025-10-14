package edu.txts.sps131025.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	// Common errors
	INTERNAL_SERVER_ERROR("COMMON_001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_INPUT_VALUE("COMMON_002", "Invalid input value", HttpStatus.BAD_REQUEST),
	METHOD_NOT_ALLOWED("COMMON_003", "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED),

	// Business errors
	USER_NOT_FOUND("USER_001", "User not found", HttpStatus.NOT_FOUND),
	USER_ALREADY_EXISTS("USER_002", "User already exists", HttpStatus.CONFLICT),
	INVALID_CREDENTIALS("AUTH_001", "Invalid credentials", HttpStatus.UNAUTHORIZED),
	ACCESS_DENIED("AUTH_002", "Access denied", HttpStatus.FORBIDDEN);

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	public static Optional<ErrorCode> of(String code) {
		return Arrays.stream(values())
				.filter(errorCode -> errorCode.getCode().equals(code))
				.findFirst();
	}
}
