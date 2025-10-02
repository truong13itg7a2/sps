package edu.txts.spsfdsd1.exception;

import edu.txts.spsfdsd1.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Xu ly nhung Exception con lai
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException e) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(9999);
		apiResponse.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<ApiResponse> runtimeExceptionHandler(AppException e) {
		ErrorCode errorCode = e.getErrorCode();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		String message = e.getFieldError().getDefaultMessage();
		ErrorCode errorCode = ErrorCode.valueOf(message);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//		String message = e.getFieldError().getDefaultMessage();
//		ErrorCode errorCode = ErrorCode.INVALID_KEYERROR_CODE;
//		ApiResponse apiResponse = new ApiResponse();
//		apiResponse.setCode(errorCode.getCode());
//		apiResponse.setMessage(message);
//		return ResponseEntity.badRequest().body(apiResponse);
//	}

}
