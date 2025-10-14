package edu.txts.sps131025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD5 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String fullName;
	String email;
	String phone;
	LocalDate birthday;

}
