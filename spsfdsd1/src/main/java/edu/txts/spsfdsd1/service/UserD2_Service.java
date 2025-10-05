package edu.txts.spsfdsd1.service;

import edu.txts.spsfdsd1.dto.request.UserD2Update;
import edu.txts.spsfdsd1.dto.request.UserD2_Create;
import edu.txts.spsfdsd1.dto.response.UserD2_Response;
import edu.txts.spsfdsd1.entity.UserD2;
import edu.txts.spsfdsd1.exception.AppException;
import edu.txts.spsfdsd1.exception.ErrorCode;
import edu.txts.spsfdsd1.mapper.UserD2_Mapper;
import edu.txts.spsfdsd1.repository.UserD2_Repository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserD2_Service {

	UserD2_Repository userD2_Repository;
	UserD2_Mapper userD2_Mapper;

	public UserD2_Response createUserD2(UserD2_Create userD2_create) {
		if (userD2_Repository.existsByUsername(userD2_create.getUsername())) {
//			throw new RuntimeException("Username already exists");
			throw new AppException(ErrorCode.USER_EXISTED);
		}
		UserD2 userD2 = userD2_Mapper.toUserD2(userD2_create);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		userD2.setPassword(passwordEncoder.encode(userD2.getPassword()));
//		userD2.setFirstName(userD2_create.getFirstName());
//		userD2.setLastName(userD2_create.getLastName());
//		userD2.setUsername(userD2_create.getUsername());
//		userD2.setPassword(userD2_create.getPassword());
//		userD2.setDob(userD2_create.getDob());
		return userD2_Mapper.toUserD2_Response(userD2_Repository.save(userD2));

	}

	public List<UserD2_Response> getAllUserD2() {
		return userD2_Mapper.toListUserD2_Response(userD2_Repository.findAll());
	}

	public UserD2_Response getUserD2(String id) {
		return userD2_Mapper.toUserD2_Response(userD2_Repository.findById(id).orElseThrow(() -> new RuntimeException(
				"User Not " +
						"Found")));
	}

	public UserD2_Response updateUserD2(String id, UserD2Update userD2Update) {
		UserD2 userD2 = userD2_Repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
		userD2_Mapper.updateUserD2(userD2Update, userD2);
//		userD2.setFirstName(userD2Update.getFirstName());
//		userD2.setLastName(userD2Update.getLastName());
//		userD2.setPassword(userD2Update.getPassword());
//		userD2.setDob(userD2Update.getDob());
		return userD2_Mapper.toUserD2_Response(userD2_Repository.save(userD2));

	}

	public void deleteUserD2(String id) {
		userD2_Repository.deleteById(id);
	}
}
