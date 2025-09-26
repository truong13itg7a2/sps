package edu.txts.pj260925.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class UserUpdate_260925v1 {
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;
}
