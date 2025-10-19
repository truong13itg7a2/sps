package edu.txts.sps181025.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD6Create {
	String username;
	String password;
	String fullName;
	LocalDateTime birthday;
}
