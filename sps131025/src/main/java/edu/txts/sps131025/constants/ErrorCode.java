package edu.txts.sps131025.constants;

public enum ErrorCode {
	// Common errors
	INTERNAL_SERVER_ERROR("COMMON_001", "Internal server error"),
	INVALID_INPUT_VALUE("COMMON_002", "Invalid input value"),
	METHOD_NOT_ALLOWED("COMMON_003", "Method not allowed"),

	// Business errors
	USER_NOT_FOUND("USER_001", "User not found"),
	USER_ALREADY_EXISTS("USER_002", "User already exists"),
	INVALID_CREDENTIALS("AUTH_001", "Invalid credentials"),
	ACCESS_DENIED("AUTH_002", "Access denied"),

	// Resource errors
	RESOURCE_NOT_FOUND("RESOURCE_001", "Resource not found"),
	DUPLICATE_RESOURCE("RESOURCE_002", "Duplicate resource");

	private final String code;
	private final String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() { return code; }
	public String getMessage() { return message; }
}
