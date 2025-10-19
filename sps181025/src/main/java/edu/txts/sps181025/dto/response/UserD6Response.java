package edu.txts.sps181025.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD6Response {
	String username;
//	String password;
	String fullName;
	LocalDateTime birthday;
}
