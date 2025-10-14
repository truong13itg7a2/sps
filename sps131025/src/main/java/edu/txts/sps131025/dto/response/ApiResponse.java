package edu.txts.sps131025.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	private final boolean success;
	private final T data;
	private final ErrorResponse error;
	@Builder.Default
	private final String timestamp = Instant.now().toString();
	private final String traceId;
	private final PageInfo page;

	// Success responses
	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.build();
	}

	public static <T> ApiResponse<T> success(T data, PageInfo pageInfo) {
		return ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.page(pageInfo)
				.build();
	}

	public static ApiResponse<Void> success() {
		return ApiResponse.<Void>builder()
				.success(true)
				.build();
	}

	// Error responses
	public static ApiResponse<Void> error(ErrorResponse error) {
		return ApiResponse.<Void>builder()
				.success(false)
				.error(error)
				.build();
	}

	public static ApiResponse<Void> error(ErrorResponse error, String traceId) {
		return ApiResponse.<Void>builder()
				.success(false)
				.error(error)
				.traceId(traceId)
				.build();
	}

	@Getter
	@Builder
	public static class PageInfo {
		private final int page;
		private final int size;
		private final long totalElements;
		private final int totalPages;
		private final boolean hasNext;
		private final boolean hasPrevious;
	}
}
