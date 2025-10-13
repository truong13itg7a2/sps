package edu.txts.spsfdsd1.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD4Create {
	String username;
	String password;
	String fullname;
	String email;
	LocalDate birthday;
}
