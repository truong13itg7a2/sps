package edu.txts.pj071025.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Common Errors (COMMON_XXX)
	INTERNAL_SERVER_ERROR("COMMON_001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_INPUT_VALUE("COMMON_002", "Invalid input value", HttpStatus.BAD_REQUEST),
	METHOD_NOT_ALLOWED("COMMON_003", "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED),
	ACCESS_DENIED("COMMON_004", "Access denied", HttpStatus.FORBIDDEN),
	RESOURCE_NOT_FOUND("COMMON_005", "Resource not found", HttpStatus.NOT_FOUND),

	// Authentication & Authorization (AUTH_XXX)
	UNAUTHORIZED("AUTH_001", "Unauthorized", HttpStatus.UNAUTHORIZED),
	INVALID_CREDENTIALS("AUTH_002", "Invalid credentials", HttpStatus.UNAUTHORIZED),
	TOKEN_EXPIRED("AUTH_003", "Token expired", HttpStatus.UNAUTHORIZED),
	INVALID_TOKEN("AUTH_004", "Invalid token", HttpStatus.UNAUTHORIZED),

	// Business Logic Errors (BUSINESS_XXX)
	USER_NOT_FOUND("BUSINESS_001", "User not found", HttpStatus.NOT_FOUND),
	USER_ALREADY_EXISTS("BUSINESS_002", "User already exists", HttpStatus.CONFLICT),
	INSUFFICIENT_BALANCE("BUSINESS_003", "Insufficient balance", HttpStatus.BAD_REQUEST),
	ORDER_ALREADY_PROCESSED("BUSINESS_004", "Order already processed", HttpStatus.CONFLICT),

	// Validation Errors (VALIDATION_XXX)
	VALIDATION_FAILED("VALIDATION_001", "Validation failed", HttpStatus.BAD_REQUEST),
	INVALID_EMAIL_FORMAT("VALIDATION_002", "Invalid email format", HttpStatus.BAD_REQUEST),
	INVALID_PHONE_FORMAT("VALIDATION_003", "Invalid phone format", HttpStatus.BAD_REQUEST),

	// External Service Errors (EXTERNAL_XXX)
	EXTERNAL_SERVICE_UNAVAILABLE("EXTERNAL_001", "External service unavailable", HttpStatus.SERVICE_UNAVAILABLE),
	EXTERNAL_SERVICE_TIMEOUT("EXTERNAL_002", "External service timeout", HttpStatus.GATEWAY_TIMEOUT);

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	public static Optional<ErrorCode> findByCode(String code) {
		return Arrays.stream(values())
				.filter(errorCode -> errorCode.getCode().equals(code))
				.findFirst();
	}
}
