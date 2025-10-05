package edu.txts.spsfdsd1.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserD2_Response {
	String username;
	//	String password = "*********";
	String firstName;
	String lastName;
	LocalDate dob;
}
