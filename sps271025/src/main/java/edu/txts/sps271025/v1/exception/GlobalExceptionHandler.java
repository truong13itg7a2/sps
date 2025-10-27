package edu.txts.sps271025.v1.exception;

import edu.txts.sps271025.v1.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.Map;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 17:09
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException e) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(1000);
		apiResponse.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	/*@ExceptionHandler(AppException.class)
	public ResponseEntity<?> handleAppException(AppException ex) {
		ErrorCode code = ex.getErrorCode();
		return ResponseEntity.status(code.getStatus())
				.body(Map.of(
						"code", code.name(),
						"message", code.getMessage()
				));
	}*/

	@ExceptionHandler(AppException.class)
	ResponseEntity<ApiResponse> appExceptionHandler(AppException e) {
		ErrorCode errorCode = e.getErrorCode();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	/*Valid cac InputField */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
	}
}
