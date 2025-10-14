package edu.txts.sps131025.exception;

import edu.txts.sps131025.constants.ErrorCode;
import edu.txts.sps131025.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	// Handle custom business exceptions
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(
			BusinessException ex,
			WebRequest request) {

		log.warn("Business exception: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.of(
				ex.getErrorCode(),
				getRequestPath(request)
		).details(ex.getDetails());

		return new ResponseEntity<>(errorResponse, getHttpStatus(ex.getErrorCode()));
	}

	// Handle validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex,
			WebRequest request) {

		Map<String, Object> details = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						FieldError::getField,
						FieldError::getDefaultMessage,
						(existing, replacement) -> existing
				));

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.INVALID_INPUT_VALUE,
				getRequestPath(request)
		).details(details);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle access denied
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(
			AccessDeniedException ex,
			WebRequest request) {

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.ACCESS_DENIED,
				getRequestPath(request)
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	// Handle resource not found
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(
			NoHandlerFoundException ex,
			WebRequest request) {

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.RESOURCE_NOT_FOUND,
				getRequestPath(request)
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// Handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllUncaughtException(
			Exception ex,
			WebRequest request) {

		log.error("Unknown error occurred: {}", ex.getMessage(), ex);

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.INTERNAL_SERVER_ERROR,
				getRequestPath(request)
		);

		// Only include stack trace in development
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			errorResponse.details(Map.of("stackTrace", getStackTrace(ex)));
		}

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private HttpStatus getHttpStatus(ErrorCode errorCode) {
		return switch (errorCode) {
			case USER_NOT_FOUND, RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
			case USER_ALREADY_EXISTS, DUPLICATE_RESOURCE -> HttpStatus.CONFLICT;
			case INVALID_CREDENTIALS, INVALID_INPUT_VALUE -> HttpStatus.BAD_REQUEST;
			case ACCESS_DENIED -> HttpStatus.FORBIDDEN;
			default -> HttpStatus.INTERNAL_SERVER_ERROR;
		};
	}

	private String getRequestPath(WebRequest request) {
		if (request instanceof ServletWebRequest servletWebRequest) {
			return servletWebRequest.getRequest().getRequestURI();
		}
		return "";
	}

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	@Autowired
	private Environment environment;
}
