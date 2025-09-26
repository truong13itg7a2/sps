package edu.txts.pj260925.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Setter
@Getter
public class ErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;

	public ErrorResponse(HttpStatus status, String message) {
		this.timestamp = LocalDateTime.now();
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
	}
}
