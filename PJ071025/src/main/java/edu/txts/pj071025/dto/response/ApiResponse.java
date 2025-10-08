package edu.txts.pj071025.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.ErrorResponse;

import java.time.Instant;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	@Builder.Default
	private final boolean success = true;

	private final T data;
	private final ErrorResponse error;

	@Builder.Default
	private final String timestamp = Instant.now().toString();

	private final String traceId;
	private final PaginationMeta pagination;

	// Success responses
	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.build();
	}

	public static <T> ApiResponse<T> success(T data, PaginationMeta pagination) {
		return ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.pagination(pagination)
				.build();
	}

	public static ApiResponse<Void> success() {
		return ApiResponse.<Void>builder()
				.success(true)
				.build();
	}

	public static <T> ApiResponse<T> success(T data, String traceId) {
		return ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.traceId(traceId)
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

	// Helper methods
	public boolean hasData() {
		return data != null;
	}

	public boolean hasError() {
		return error != null;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class PaginationMeta {
		private final int page;
		private final int size;
		private final long totalElements;
		private final int totalPages;
		private final boolean first;
		private final boolean last;
		private final boolean hasNext;
		private final boolean hasPrevious;

		public static PaginationMeta from(Page<?> page) {
			return PaginationMeta.builder()
					.page(page.getNumber())
					.size(page.getSize())
					.totalElements(page.getTotalElements())
					.totalPages(page.getTotalPages())
					.first(page.isFirst())
					.last(page.isLast())
					.hasNext(page.hasNext())
					.hasPrevious(page.hasPrevious())
					.build();
		}
	}
}
