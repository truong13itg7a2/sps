package edu.txts.pj260925.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class User_260925v1 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;

}
