package edu.txts.spsfdsd1.service.impl;

import edu.txts.spsfdsd1.dto.request.UserD1_Create;
import edu.txts.spsfdsd1.dto.request.UserD1_Update;
import edu.txts.spsfdsd1.dto.response.UserD1_Response;
import edu.txts.spsfdsd1.entity.UserD1;
import edu.txts.spsfdsd1.mapper.UserD1_Mapper;
import edu.txts.spsfdsd1.repository.UserD1_Repository;
import edu.txts.spsfdsd1.service.itfc.UserD1_Service;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserD1_ServieImpl implements UserD1_Service {
	UserD1_Repository userD1Repository;
	UserD1_Mapper userD1_Mapper;

	@Override
	public UserD1_Response creatUserD1(UserD1_Create userD1_Create) {
		if (userD1Repository.existsByUsername(userD1_Create.getUsername())) {
			return null;
		}
		UserD1 userD1 = userD1Repository.save(userD1_Mapper.toUserD1(userD1_Create));
		//		UserD1 userD1 = userD1_Mapper.toUserD1(userD1_Response);
		return userD1_Mapper.toUserD1_Response(userD1);

	}

	@Override
	public UserD1_Response getUserD1(String id) {
		UserD1 userD1 = userD1Repository.findById(id).orElseThrow(() -> new RuntimeException("User D1 not found"));
		return userD1_Mapper.toUserD1_Response(userD1);
	}

	@Override
	public List<UserD1_Response> getAllUserD1() {
		List<UserD1> userD1List = userD1Repository.findAll();
		return userD1_Mapper.toUserD1_Response(userD1List);
	}

	@Override
	public UserD1_Response updateUserD1(String id, UserD1_Update userD1_Update) {
		return null;
	}

	@Override
	public void deleteUserD1(String id) {
		userD1Repository.deleteById(id);
	}
}
