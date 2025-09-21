package edu.txts.p210925.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User210925_v2Request {
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}

