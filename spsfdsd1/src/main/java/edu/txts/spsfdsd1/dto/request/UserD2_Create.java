package edu.txts.spsfdsd1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserD2_Create {
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 20, message = "USERNAME_INVALID")
	String username;
	@Size(min = 6, max = 255, message = "PASSWORD_INVALID")
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}
