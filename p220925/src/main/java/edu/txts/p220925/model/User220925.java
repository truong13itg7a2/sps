package edu.txts.p220925.model;

import edu.txts.p220925.exception.ErrorCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User220925 {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Size(min = 6, message = "USERNAME_INVALID")
	String username;
	@Size(min = 8, message = "INVALID_PASSWORD")
	String password;
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
	int age;
	double income;
}
