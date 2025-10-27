package edu.txts.sps271025.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 21:23
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Getter
@AllArgsConstructor
public enum ErrorCode {

	USER_EXISTED(1001, "Username already existed..."),
	;

	private int code;
	private String message;

}
