package edu.txts.pj071025.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	@Builder.Default
	private final String timestamp = Instant.now().toString();

	private final String code;
	private final String message;
	private final String path;
	private final Map<String, Object> details;
	private final String traceId;
	private final List<FieldError> fieldErrors;

	// Factory methods
	public static ErrorResponse of(ErrorCode errorCode, String path) {
		return ErrorResponse.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.path(path)
				.build();
	}

	public static ErrorResponse of(ErrorCode errorCode, String path, String traceId) {
		return ErrorResponse.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.path(path)
				.traceId(traceId)
				.build();
	}

	public static ErrorResponse of(ErrorCode errorCode, String path, Map<String, Object> details) {
		return ErrorResponse.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.path(path)
				.details(details)
				.build();
	}

	// Fluent API for building
	public ErrorResponse withDetail(String key, Object value) {
		Map<String, Object> newDetails = this.details != null ?
				new HashMap<>(this.details) : new HashMap<>();
		newDetails.put(key, value);

		return ErrorResponse.builder()
				.code(this.code)
				.message(this.message)
				.path(this.path)
				.details(newDetails)
				.traceId(this.traceId)
				.fieldErrors(this.fieldErrors)
				.build();
	}

	public ErrorResponse withTraceId(String traceId) {
		return ErrorResponse.builder()
				.code(this.code)
				.message(this.message)
				.path(this.path)
				.details(this.details)
				.traceId(traceId)
				.fieldErrors(this.fieldErrors)
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class FieldError {
		private final String field;
		private final String message;
		private final Object rejectedValue;

		public static FieldError of(String field, String message, Object rejectedValue) {
			return FieldError.builder()
					.field(field)
					.message(message)
					.rejectedValue(rejectedValue)
					.build();
		}
	}
}
