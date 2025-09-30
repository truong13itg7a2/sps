package edu.txts.spsfdsd1.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD1_Update {
	String password;
	String lastname;
	String firstname;
	LocalDate birthday;
}
