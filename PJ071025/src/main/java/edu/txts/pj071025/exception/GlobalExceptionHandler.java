package edu.txts.pj071025.exception;

import edu.txts.pj071025.dto.response.ApiResponse;
import edu.txts.pj071025.dto.response.ErrorCode;
import edu.txts.pj071025.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final Environment environment;
	private final Tracer tracer;

	private static final List<String> DEVELOPMENT_PROFILES = Arrays.asList("dev", "local", "test");

	// Handle Business Exceptions
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(
			BusinessException ex, HttpServletRequest request) {

		log.warn("Business exception - Code: {}, Message: {}",
				ex.getErrorCode().getCode(), ex.getMessage());

		ErrorResponse errorResponse = buildErrorResponse(ex, request);
		return createErrorResponse(errorResponse, ex.getHttpStatus());
	}

	// Handle Validation Exceptions
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationException(
			ValidationException ex, HttpServletRequest request) {

		log.warn("Validation exception: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ex.getErrorCode().getCode())
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.fieldErrors(ex.getFieldErrors())
				.traceId(getTraceId())
				.build();

		return createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle Method Argument Not Valid
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpServletRequest request) {

		List<ErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> ErrorResponse.FieldError.of(
						error.getField(),
						error.getDefaultMessage(),
						error.getRejectedValue()
				))
				.collect(Collectors.toList());

		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCode.VALIDATION_FAILED.getCode())
				.message(ErrorCode.VALIDATION_FAILED.getMessage())
				.path(request.getRequestURI())
				.fieldErrors(fieldErrors)
				.traceId(getTraceId())
				.build();

		return createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle Constraint Violation
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(
			ConstraintViolationException ex, HttpServletRequest request) {

		List<ErrorResponse.FieldError> fieldErrors = ex.getConstraintViolations()
				.stream()
				.map(violation -> {
					String field = violation.getPropertyPath().toString();
					return ErrorResponse.FieldError.of(
							field,
							violation.getMessage(),
							violation.getInvalidValue()
					);
				})
				.collect(Collectors.toList());

		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCode.VALIDATION_FAILED.getCode())
				.message("Constraint violation")
				.path(request.getRequestURI())
				.fieldErrors(fieldErrors)
				.traceId(getTraceId())
				.build();

		return createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle Access Denied
	@ExceptionHandler({AccessDeniedException.class, AuthorizationServiceException.class})
	public ResponseEntity<ApiResponse<Void>> handleAccessDenied(
			Exception ex, HttpServletRequest request) {

		log.warn("Access denied for request: {}", request.getRequestURI());

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.ACCESS_DENIED,
				request.getRequestURI(),
				getTraceId()
		);

		return createErrorResponse(errorResponse, HttpStatus.FORBIDDEN);
	}

	// Handle Authentication Exception
	@ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
	public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(
			Exception ex, HttpServletRequest request) {

		log.warn("Authentication failed: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.INVALID_CREDENTIALS,
				request.getRequestURI(),
				getTraceId()
		);

		return createErrorResponse(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	// Handle No Handler Found
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleNoHandlerFound(
			NoHandlerFoundException ex, HttpServletRequest request) {

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.RESOURCE_NOT_FOUND,
				request.getRequestURI(),
				getTraceId()
		);

		return createErrorResponse(errorResponse, HttpStatus.NOT_FOUND);
	}

	// Handle Http Message Not Readable
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpServletRequest request) {

		log.warn("Malformed JSON request: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.INVALID_INPUT_VALUE,
				request.getRequestURI(),
				getTraceId()
		).withDetail("reason", "Malformed JSON request");

		return createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle All Uncaught Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleAllUncaughtException(
			Exception ex, HttpServletRequest request) {

		log.error("Unhandled exception occurred - Path: {}, Method: {}",
				request.getRequestURI(), request.getMethod(), ex);

		ErrorResponse.ErrorResponseBuilder responseBuilder = ErrorResponse.builder()
				.code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
				.message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
				.path(request.getRequestURI())
				.traceId(getTraceId());

		// Include stack trace in development
		if (isDevelopmentEnvironment()) {
			responseBuilder.details(Map.of(
					"stackTrace", getStackTrace(ex),
					"exceptionType", ex.getClass().getName()
			));
		}

		return createErrorResponse(responseBuilder.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Helper methods
	private ErrorResponse buildErrorResponse(BaseException ex, HttpServletRequest request) {
		ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder()
				.code(ex.getErrorCode().getCode())
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.traceId(getTraceId());

		if (!ex.getDetails().isEmpty()) {
			builder.details(ex.getDetails());
		}

		return builder.build();
	}

	private ResponseEntity<ApiResponse<Void>> createErrorResponse(
			ErrorResponse errorResponse, HttpStatus status) {

		ApiResponse<Void> response = ApiResponse.error(errorResponse);
		return ResponseEntity.status(status).body(response);
	}

	private String getTraceId() {
		return tracer.currentSpan().context().traceId();
	}

	private boolean isDevelopmentEnvironment() {
		return Arrays.stream(environment.getActiveProfiles())
				.anyMatch(DEVELOPMENT_PROFILES::contains);
	}

	private String getStackTrace(Exception ex) {
		return Arrays.stream(ex.getStackTrace())
				.limit(10)
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n"));
	}
}
