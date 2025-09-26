package edu.txts.pj260925.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum ErrorCode {
	USER_EXISTED(1001, "User existed...", HttpStatus.BAD_REQUEST),;
	int code;
	String message;
	HttpStatus httpStatus;
}
