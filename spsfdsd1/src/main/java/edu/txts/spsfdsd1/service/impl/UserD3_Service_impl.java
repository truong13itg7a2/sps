package edu.txts.spsfdsd1.service.impl;

import edu.txts.spsfdsd1.dto.request.UserD3_Request_Create;
import edu.txts.spsfdsd1.dto.request.UserD3_Request_Update;
import edu.txts.spsfdsd1.dto.response.UserD3_Response;
import edu.txts.spsfdsd1.entity.UserD3;
import edu.txts.spsfdsd1.mapper.UserD3_Mapper;
import edu.txts.spsfdsd1.repository.UserD3_Repository;
import edu.txts.spsfdsd1.service.itfc.Itfc_UserD3_Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserD3_Service_impl implements Itfc_UserD3_Service {
	UserD3_Repository userD3_Repository;
	UserD3_Mapper userD3_Mapper;

	@Override
	public UserD3_Response createUserD3(UserD3_Request_Create userD3_Request_Create) {
		if (userD3_Repository.existsByUsername(userD3_Request_Create.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		UserD3 userD3 = userD3_Mapper.toUserD3(userD3_Request_Create);
//		UserD3_Response userD3_Response = userD3_Mapper.toUserD3_ResponseFURC(userD3_Request_Create);
		return userD3_Mapper.toUserD3_Response(userD3_Repository.save(userD3));
	}

	@Override
	public UserD3_Response getUserD3(String userId) {

		return userD3_Mapper.toUserD3_Response(userD3_Repository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found")));
	}

	@Override
	public List<UserD3_Response> getAllUserD3v1() {
		return userD3_Mapper.toListUserD3_Response(userD3_Repository.findAll());
	}

	@Override
	public List<UserD3> getAllUserD3v2() {
		return userD3_Repository.findAll();
	}

	@Override
	public UserD3_Response updateUserD3(String userId, UserD3_Request_Update userD3_Request_Update) {
		UserD3 userD3 = userD3_Mapper.toUserD3(getUserD3(userId));
		userD3_Mapper.updateUserD3(userD3, userD3_Request_Update);

		return userD3_Mapper.toUserD3_Response(userD3_Repository.save(userD3));
	}

	@Override
	public void deleteUserD3(String userId) {
		UserD3 userD3 = userD3_Mapper.toUserD3(getUserD3(userId));
		userD3_Repository.delete(userD3);
	}
}
