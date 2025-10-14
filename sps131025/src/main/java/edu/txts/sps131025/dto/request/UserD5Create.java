package edu.txts.sps131025.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD5Create {
	@NotBlank
	@Size(min = 3, max = 50)
	String username;

	@NotBlank
	@Size(min = 6)
	String password;

	String fullName;

	@NotBlank
	@Email
	String email;

	String phone;
	LocalDate birthday;
}
