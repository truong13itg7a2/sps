package edu.txts.sps131025.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.txts.sps131025.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashMap;
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

	// Static factory methods
	public static ErrorResponseBuilder from(ErrorCode errorCode) {
		return ErrorResponse.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage());
	}

	public static ErrorResponse of(ErrorCode errorCode, String path) {
		return ErrorResponse.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.path(path)
				.build();
	}
}
