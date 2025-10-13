package edu.txts.spsfdsd1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD4 {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String fullname;
	String email;
	LocalDate birthday;
}
