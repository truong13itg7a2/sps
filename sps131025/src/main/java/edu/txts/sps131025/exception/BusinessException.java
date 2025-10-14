package edu.txts.sps131025.exception;

import edu.txts.sps131025.constants.ErrorCode;

import java.util.HashMap;
import java.util.Map;

public class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;
	private final Map<String, Object> details;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.details = new HashMap<>();
	}

	public BusinessException(ErrorCode errorCode, String customMessage) {
		super(customMessage);
		this.errorCode = errorCode;
		this.details = new HashMap<>();
	}

	public BusinessException(ErrorCode errorCode, Map<String, Object> details) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.details = details;
	}

	// Getters
	public ErrorCode getErrorCode() { return errorCode; }
	public Map<String, Object> getDetails() { return details; }
}
