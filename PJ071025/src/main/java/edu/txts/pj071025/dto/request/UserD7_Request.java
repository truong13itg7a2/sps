package edu.txts.pj071025.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD7_Request {
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}
