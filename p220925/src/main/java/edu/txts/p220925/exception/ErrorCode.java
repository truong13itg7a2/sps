package edu.txts.p220925.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
//@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum ErrorCode {
	USER_EXISTED(1002, "User Existed!!!"),
	UNCATEGORIZED_EXCEPTION(1003, "Uncategorized!!!"),
	USERNAME_INVALID(1004, "Username Existed!!!"),;
	private int code;
	private String message;

	 ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}



}
