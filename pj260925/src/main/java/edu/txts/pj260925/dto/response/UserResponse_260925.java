package edu.txts.pj260925.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse_260925 {
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;
}
