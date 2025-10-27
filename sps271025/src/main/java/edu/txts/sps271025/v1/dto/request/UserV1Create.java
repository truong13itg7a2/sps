package edu.txts.sps271025.v1.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 16:12
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserV1Create {
	@Size(min = 6, max = 24, message = "Username must be 6 character...")
	String username;
	@Size(min = 8, message = "Password must be 8 character...")
	String password;
	@NotNull
	String fullName;
	LocalDate birthDate;
}
