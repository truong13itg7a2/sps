package edu.txts.p210925.model_entity_domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User210925_v2 {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	 String id;
	 String username;
	 String password;
	 String firstName;
	 String lastName;
	 LocalDate dob;


}
