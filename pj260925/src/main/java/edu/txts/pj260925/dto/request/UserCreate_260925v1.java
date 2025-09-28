package edu.txts.pj260925.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreate_260925v1 {
	@Size(min = 6, max = 255,message = "Username must start with 6 characters")
	@NotBlank(message = "Username not Blank, please...")
	@NotEmpty(message = "Username not Empty, please...")
	String username;
	@NotEmpty(message = "Password not Empty, please...")
	@Size(min = 8, max = 255,message = "Password must start with 8 characters")
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;
}
