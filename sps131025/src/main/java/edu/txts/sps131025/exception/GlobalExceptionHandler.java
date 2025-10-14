package edu.txts.sps131025.exception;

import edu.txts.sps131025.constants.ErrorCode;
import edu.txts.sps131025.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final Environment environment;
	private final ExceptionMetrics exceptionMetrics;

	private static final List<String> DEVELOPMENT_PROFILES = Arrays.asList("dev", "local", "test");

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(
			BusinessException ex,
			HttpServletRequest request) {

		log.warn("Business exception - Code: {}, Message: {}",
				ex.getErrorCode().getCode(), ex.getMessage());

		exceptionMetrics.recordBusinessException(ex.getErrorCode());

		ErrorResponse errorResponse = buildErrorResponse(ex, request);
		return new ResponseEntity<>(errorResponse, ex.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, Object> details = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						FieldError::getField,
						fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""),
						(existing, replacement) -> existing
				));

		ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_INPUT_VALUE)
				.path(request.getRequestURI())
				.details(details)
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(
			ConstraintViolationException ex,
			HttpServletRequest request) {

		Map<String, Object> details = ex.getConstraintViolations()
				.stream()
				.collect(Collectors.toMap(
						violation -> violation.getPropertyPath().toString(),
						ConstraintViolation::getMessage
				));

		ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_INPUT_VALUE)
				.path(request.getRequestURI())
				.details(details)
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({AccessDeniedException.class, AuthorizationServiceException.class})
	public ResponseEntity<ErrorResponse> handleAccessDenied(
			Exception ex,
			HttpServletRequest request) {

		log.warn("Access denied for request: {}", request.getRequestURI());

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.ACCESS_DENIED,
				request.getRequestURI()
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(
			NoHandlerFoundException ex,
			HttpServletRequest request) {

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.RESOURCE_NOT_FOUND,
				request.getRequestURI()
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpServletRequest request) {

		log.warn("Malformed JSON request: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_INPUT_VALUE)
				.path(request.getRequestURI())
				.message("Malformed JSON request")
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllUncaughtException(
			Exception ex,
			HttpServletRequest request) {

		log.error("Unknown error occurred - Path: {}, Method: {}",
				request.getRequestURI(), request.getMethod(), ex);

		exceptionMetrics.recordGenericException();

		ErrorResponse.ErrorResponseBuilder responseBuilder = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR)
				.path(request.getRequestURI());

		// Chỉ include stack trace trong môi trường development
		if (isDevelopmentEnvironment()) {
			responseBuilder.details(Map.of("stackTrace", getStackTrace(ex)));
		}

		return new ResponseEntity<>(responseBuilder.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse buildErrorResponse(BusinessException ex, HttpServletRequest request) {
		ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder()
				.code(ex.getErrorCode().getCode())
				.message(Optional.ofNullable(ex.getCustomMessage()).orElse(ex.getErrorCode().getMessage()))
				.path(request.getRequestURI())
				.details(ex.getDetails().isEmpty() ? null : ex.getDetails());

		// Thêm traceId nếu có
		String traceId = getTraceId(request);
		if (traceId != null) {
			builder.traceId(traceId);
		}

		return builder.build();
	}

	private boolean isDevelopmentEnvironment() {
		return Arrays.stream(environment.getActiveProfiles())
				.anyMatch(DEVELOPMENT_PROFILES::contains);
	}

	private String getStackTrace(Exception ex) {
		return Arrays.stream(ex.getStackTrace())
				.limit(10) // Giới hạn stack trace để tránh response quá lớn
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n"));
	}

	private String getTraceId(HttpServletRequest request) {
		// Lấy traceId từ header hoặc MDC (cho distributed tracing)
		return Optional.ofNullable(request.getHeader("X-Trace-Id"))
				.orElseGet(() -> MDC.get("traceId"));
	}
}
