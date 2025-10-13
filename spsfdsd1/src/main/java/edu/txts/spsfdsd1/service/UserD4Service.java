package edu.txts.spsfdsd1.service;

import edu.txts.spsfdsd1.dto.request.UserD4Create;
import edu.txts.spsfdsd1.dto.request.UserD4Update;
import edu.txts.spsfdsd1.dto.response.UserD4Response;
import edu.txts.spsfdsd1.entity.UserD4;
import edu.txts.spsfdsd1.mapper.UserD4Mapper;
import edu.txts.spsfdsd1.repository.UserD4Repository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserD4Service {
	UserD4Repository userD4Repository;
	UserD4Mapper userD4Mapper;

	public UserD4Response createUserD4(UserD4Create userD4Create) {
		if (userD4Repository.existsByUsername(userD4Create.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		UserD4 userD4 = userD4Repository.save(userD4Mapper.toUserD4(userD4Create));
		return userD4Mapper.toUserD4Response(userD4);
	}

	public List<UserD4> getAllUserD4() {
		return userD4Repository.findAll();
	}

	public UserD4Response getUserD4ById(String id) {
		return userD4Mapper.toUserD4Response(
				userD4Repository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
	}

	public UserD4Response getUserD4ByUsername(String usernameOrEmail) {
		if (!(userD4Repository.existsByUsername(usernameOrEmail) || userD4Repository.existsByEmail(usernameOrEmail))) {
			throw new RuntimeException("User not found");
		}
		UserD4 userD4 = userD4Repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		return userD4Mapper.toUserD4Response(userD4);
	}

	public UserD4Response updateUserD4(String id, UserD4Update userD4Update) {
		UserD4 userD4 = userD4Mapper.toUserD4(getUserD4ById(id));
		userD4Mapper.updateUserD4(userD4Update, userD4);
		return userD4Mapper.toUserD4Response(userD4Repository.save(userD4));
	}

	public void deleteUserD4(String id) {
		UserD4 userD4 = userD4Mapper.toUserD4(getUserD4ById(id));
		userD4Repository.delete(userD4);
	}
}
