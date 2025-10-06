package edu.txts.spsfdsd1.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD3_Request_Update {
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthDate;
}
