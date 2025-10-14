package edu.txts.sps131025.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class ApiResponse<T> {
	private boolean success;
	private T data;
	private ErrorResponse error;
	private String timestamp;

	public ApiResponse(boolean success, T data, ErrorResponse error) {
		this.success = success;
		this.data = data;
		this.error = error;
		this.timestamp = Instant.now().toString();
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, data, null);
	}

	public static ApiResponse<?> error(ErrorResponse error) {
		return new ApiResponse<>(false, null, error);
	}

	// Getters and Setters
	// ...
}
