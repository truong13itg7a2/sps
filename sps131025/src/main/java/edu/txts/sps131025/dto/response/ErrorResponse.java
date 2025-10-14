package edu.txts.sps131025.dto.response;

import edu.txts.sps131025.constants.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
public class ErrorResponse {
	private String timestamp;
	private String code;
	private String message;
	private String path;
	private Map<String, Object> details;
	private String traceId; // For distributed tracing

	public ErrorResponse(String code, String message, String path) {
		this.timestamp = Instant.now().toString();
		this.code = code;
		this.message = message;
		this.path = path;
		this.details = new HashMap<>();
	}

	// Builder pattern
	public static ErrorResponse of(ErrorCode errorCode, String path) {
		return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), path);
	}

	public ErrorResponse details(Map<String, Object> details) {
		this.details = details;
		return this;
	}

	public ErrorResponse traceId(String traceId) {
		this.traceId = traceId;
		return this;
	}

	// Getters and Setters
	// ...
}
