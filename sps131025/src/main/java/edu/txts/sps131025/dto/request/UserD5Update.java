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
public class UserD5Update {
	@Size(min = 6)
	String password;

	String fullName;

	@Email
	String email;
	String phone;
	LocalDate birthday;
}
