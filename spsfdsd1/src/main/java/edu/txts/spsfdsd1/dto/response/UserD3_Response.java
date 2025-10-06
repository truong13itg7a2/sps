package edu.txts.spsfdsd1.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD3_Response {
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthDate;
	String description = "This is a UserD3_Response";
}
