package edu.txts.p220925.exception;

import edu.txts.p220925.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//Xu ly nhung Exception khac
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> exception(RuntimeException e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		ErrorCode errorCode = e.getCo
		ApiResponse response = new ApiResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<ApiResponse> appException(AppException e) {
		ErrorCode errorCode = e.getErrorCode();
		ApiResponse response = new ApiResponse();
		response.setCode(errorCode.getCode());
		response.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValid(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
	}

}
