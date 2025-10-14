package edu.txts.sps131025.exception;

import edu.txts.sps131025.constants.ErrorCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;
	private final transient Map<String, Object> details;
	private final String customMessage;

	// Private constructor để force sử dụng factory methods
	private BusinessException(ErrorCode errorCode, String customMessage, Map<String, Object> details) {
		super(customMessage != null ? customMessage : errorCode.getMessage());
		this.errorCode = errorCode;
		this.customMessage = customMessage;
		this.details = details != null ? new HashMap<>(details) : new HashMap<>();
	}

	// Factory methods
	public static BusinessException of(ErrorCode errorCode) {
		return new BusinessException(errorCode, null, null);
	}

	public static BusinessException of(ErrorCode errorCode, String customMessage) {
		return new BusinessException(errorCode, customMessage, null);
	}

	public static BusinessException of(ErrorCode errorCode, Map<String, Object> details) {
		return new BusinessException(errorCode, null, details);
	}

	public static BusinessException of(ErrorCode errorCode, String customMessage, Map<String, Object> details) {
		return new BusinessException(errorCode, customMessage, details);
	}

	// Fluent API for details
	public BusinessException withDetail(String key, Object value) {
		this.details.put(key, value);
		return this;
	}

	public BusinessException withDetails(Map<String, Object> additionalDetails) {
		this.details.putAll(additionalDetails);
		return this;
	}
}
