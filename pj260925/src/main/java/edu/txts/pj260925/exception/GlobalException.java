package edu.txts.pj260925.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalException {

	/*@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> runtimeExceptionHandler(RuntimeException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}*/
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
		/*Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());*/
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", "Internal Server Error");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", "Validation Error");

		// Lấy danh sách lỗi field
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("messages", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleEntityNotFound(EntityNotFoundException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", "Not Found");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
