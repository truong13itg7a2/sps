package edu.txts.sps271025.v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 16:03
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserV1 {
	@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String username;
	String password;
	String fullName;
	LocalDate birthDate;


}
