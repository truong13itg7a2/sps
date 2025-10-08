package edu.txts.pj071025.dto.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

@Component
public class ResponseWrapper {

	private final Tracer tracer;

	public ResponseWrapper(Tracer tracer) {
		this.tracer = tracer;
	}

	public <T> ResponseEntity<ApiResponse<T>> success(T data) {
		return ResponseEntity.ok(ApiResponse.success(data));
	}

	public <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus status) {
		return ResponseEntity.status(status).body(ApiResponse.success(data));
	}

	public <T> ResponseEntity<ApiResponse<T>> success(T data, Page<?> page) {
		ApiResponse.PaginationMeta pagination = ApiResponse.PaginationMeta.from(page);
		ApiResponse<T> response = ApiResponse.<T>builder()
				.success(true)
				.data(data)
				.pagination(pagination)
				.build();
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ApiResponse<Void>> success() {
		return ResponseEntity.ok(ApiResponse.success());
	}

	public ResponseEntity<ApiResponse<Void>> created() {
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
	}

	public <T> ResponseEntity<ApiResponse<T>> created(T data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(data));
	}

	public ResponseEntity<ApiResponse<Void>> noContent() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success());
	}

	public ResponseEntity<ApiResponse<Void>> error(ErrorResponse error, HttpStatus status) {
		String traceId = getTraceId();
		ApiResponse<Void> response = ApiResponse.error(error, traceId);
		return ResponseEntity.status(status).body(response);
	}

	private String getTraceId() {
		return tracer.currentSpan().context().traceId();
	}
}
