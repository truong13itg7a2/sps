package edu.txts.pj260925.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class UserCreate_260925v1 {
	@Size(min = 6, max = 50,message = "Username must start with 6 characters")
	@NotBlank(message = "Username not Blank, please...")
			@NotEmpty(message = "Username not Empty, please...")
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;
}
