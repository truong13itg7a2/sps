package edu.txts.spsfdsd1.exception;

public enum ErrorCode {
	USER_EXISTED(1001, "User already exists, try again!"),
	UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception!"),
	USERNAME_INVALID(1003, "Username is invalid!"),
	PASSWORD_INVALID(1004, "Password is invalid!"),
	INVALID_KEYERROR_CODE(1005, "Invalid Key Error!"),
	USER_NOT_EXISTED(1006, "User Not Existed!"),
	AUTHENTICATED_EXCEPTION(1007, "Authenticated Exception!"),
	;

	private int code;
	private String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
