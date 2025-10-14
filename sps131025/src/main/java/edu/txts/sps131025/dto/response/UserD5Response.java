package edu.txts.sps131025.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD5Response {
	String username;
	String password;
	String fullName;
	String email;
	String phone;
	LocalDate birthday;
}
