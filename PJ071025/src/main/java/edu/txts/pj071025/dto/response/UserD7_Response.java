package edu.txts.pj071025.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD7_Response {
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}
