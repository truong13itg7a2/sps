package edu.txts.pj071025.service;

import edu.txts.pj071025.dto.mapper.UserD7_Mapper;
import edu.txts.pj071025.dto.request.UserD7_Request;
import edu.txts.pj071025.dto.request.UserD7_Update;
import edu.txts.pj071025.dto.response.UserD7_Response;
import edu.txts.pj071025.model.UserD7;
import edu.txts.pj071025.repository.UserD7_Repository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserD7_ServiceImpl implements ItfcUserD7_Service{
	UserD7_Repository userD7Repository;
	UserD7_Mapper userD7Mapper;
//	PasswordEncoder passwordEncoder;
	@Override
	public UserD7_Response createUser(UserD7_Request userD7_request) {
		UserD7 userD7 = userD7Mapper.toUserD7(userD7_request);
		return userD7Mapper.toUserD7_Response(userD7Repository.save(userD7));
	}

	@Override
	public List<UserD7> getUsers() {
		return userD7Repository.findAll();
	}

	@Override
	public Optional<UserD7> getUserById(String id) {
		UserD7 userD7 = userD7Repository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
		return Optional.ofNullable(userD7);
	}

	@Override
	public Optional<UserD7_Response> getUserByUsername(String username) {
		if (!userD7Repository.existsByUsername(username)){
			throw new RuntimeException("User Not Found");
		}
		return Optional.ofNullable(userD7Mapper.toUserD7_Response(userD7Repository.findByUsername(username)));
	}

	@Override
	public UserD7_Response updateUser(String id, UserD7_Update userD7_update) {
		Optional<UserD7> userD7 = getUserById(id);
		userD7Mapper.updateUserD7(userD7, userD7_update);
		return userD7Mapper.toUserD7_Response(userD7Repository.save(userD7.get()));
	}

	@Override
	public void deleteUser(String id) {
		userD7Repository.deleteById(id);
	}
}
