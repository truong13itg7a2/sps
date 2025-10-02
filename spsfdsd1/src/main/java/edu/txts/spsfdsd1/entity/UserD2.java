package edu.txts.spsfdsd1.entity;

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
@Getter
@Setter
public class UserD2 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
}
