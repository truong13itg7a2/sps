package edu.txts.sps271025.v1.exception;

import lombok.Getter;
import lombok.Setter;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 21:28
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Setter
@Getter
public class AppException extends RuntimeException{
	private ErrorCode errorCode;

	public AppException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}


}
