package edu.txts.spsfdsd1.service;

import edu.txts.spsfdsd1.dto.request.UserD2Update;
import edu.txts.spsfdsd1.dto.request.UserD2_Create;
import edu.txts.spsfdsd1.entity.UserD2;
import edu.txts.spsfdsd1.exception.AppException;
import edu.txts.spsfdsd1.exception.ErrorCode;
import edu.txts.spsfdsd1.repository.UserD2_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserD2_Service {
	@Autowired
	private UserD2_Repository userD2_Repository;

	public UserD2 createUserD2(UserD2_Create userD2_create) {
		if (userD2_Repository.existsByUsername(userD2_create.getUsername())) {
//			throw new RuntimeException("Username already exists");
			throw new AppException(ErrorCode.USER_EXISTED);
		}
		UserD2 userD2 = new UserD2();
		userD2.setFirstName(userD2_create.getFirstName());
		userD2.setLastName(userD2_create.getLastName());
		userD2.setUsername(userD2_create.getUsername());
		userD2.setPassword(userD2_create.getPassword());
		userD2.setDob(userD2_create.getDob());
		return userD2_Repository.save(userD2);

	}

	public List<UserD2> getAllUserD2() {
		return userD2_Repository.findAll();
	}

	public UserD2 getUserD2(String id) {
		return userD2_Repository.findById(id).orElseThrow(() -> new RuntimeException("User D2 Not Found"));
	}

	public UserD2 updateUserD2(String id, UserD2Update userD2Update) {
		UserD2 userD2 = getUserD2(id);
		userD2.setFirstName(userD2Update.getFirstName());
		userD2.setLastName(userD2Update.getLastName());
		userD2.setPassword(userD2Update.getPassword());
		userD2.setDob(userD2Update.getDob());
		return userD2_Repository.save(userD2);

	}
	public void deleteUserD2(String id) {
		userD2_Repository.deleteById(id);
	}
}
