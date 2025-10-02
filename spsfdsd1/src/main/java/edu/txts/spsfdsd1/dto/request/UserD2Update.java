package edu.txts.spsfdsd1.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserD2Update {
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}
