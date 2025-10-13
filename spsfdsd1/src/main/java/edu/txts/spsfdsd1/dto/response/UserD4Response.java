package edu.txts.spsfdsd1.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD4Response {
	String password;
	String fullname;
	String email;
	LocalDate birthday;
}
