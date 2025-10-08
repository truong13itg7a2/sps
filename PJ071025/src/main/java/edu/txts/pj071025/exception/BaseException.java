package edu.txts.pj071025.exception;

import edu.txts.pj071025.dto.response.ErrorCode;
import edu.txts.pj071025.dto.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.*;

@Getter
public abstract class BaseException extends RuntimeException {
	private final ErrorCode errorCode;
	private final Map<String, Object> details;

	protected BaseException(ErrorCode errorCode, String message, Map<String, Object> details) {
		super(message != null ? message : errorCode.getMessage());
		this.errorCode = errorCode;
		this.details = details != null ? new HashMap<>(details) : new HashMap<>();
	}

	public HttpStatus getHttpStatus() {
		return errorCode.getHttpStatus();
	}

	// Fluent API
	public BaseException withDetail(String key, Object value) {
		this.details.put(key, value);
		return this;
	}
}

// Business Exception
public class BusinessException extends BaseException {

	public BusinessException(ErrorCode errorCode) {
		super(errorCode, null, null);
	}

	public BusinessException(ErrorCode errorCode, String message) {
		super(errorCode, message, null);
	}

	public BusinessException(ErrorCode errorCode, Map<String, Object> details) {
		super(errorCode, null, details);
	}

	public BusinessException(ErrorCode errorCode, String message, Map<String, Object> details) {
		super(errorCode, message, details);
	}

	// Factory methods
	public static BusinessException of(ErrorCode errorCode) {
		return new BusinessException(errorCode);
	}

	public static BusinessException of(ErrorCode errorCode, String message) {
		return new BusinessException(errorCode, message);
	}

	public static BusinessException of(ErrorCode errorCode, Map<String, Object> details) {
		return new BusinessException(errorCode, details);
	}
}

// Validation Exception
public class ValidationException extends BusinessException {
	private final List<ErrorResponse.FieldError> fieldErrors;

	public ValidationException(List<ErrorResponse.FieldError> fieldErrors) {
		super(ErrorCode.VALIDATION_FAILED, "Validation failed");
		this.fieldErrors = fieldErrors != null ? new ArrayList<>(fieldErrors) : new ArrayList<>();
	}

	public ValidationException(String field, String message, Object rejectedValue) {
		super(ErrorCode.VALIDATION_FAILED, "Validation failed");
		this.fieldErrors = List.of(
				ErrorResponse.FieldError.of(field, message, rejectedValue)
		);
	}

	public List<ErrorResponse.FieldError> getFieldErrors() {
		return Collections.unmodifiableList(fieldErrors);
	}
}

// System Exception
public class SystemException extends BaseException {

	public SystemException(ErrorCode errorCode) {
		super(errorCode, null, null);
	}

	public SystemException(ErrorCode errorCode, String message) {
		super(errorCode, message, null);
	}

	public SystemException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause.getMessage(), null);
		initCause(cause);
	}
}

// Not Found Exception
public class NotFoundException extends BusinessException {

	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

	public NotFoundException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	public static NotFoundException of(ErrorCode errorCode) {
		return new NotFoundException(errorCode);
	}

	public static NotFoundException of(ErrorCode errorCode, String resource, Object identifier) {
		return new NotFoundException(errorCode,
				String.format("%s not found with identifier: %s", resource, identifier))
				.withDetail("resource", resource)
				.withDetail("identifier", identifier);
	}
}
