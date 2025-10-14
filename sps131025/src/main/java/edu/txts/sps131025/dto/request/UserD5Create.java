package edu.txts.sps131025.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD5Create {
	String username;
	String password;
	String fullName;
	String email;
	String phone;
	LocalDate birthday;
}
