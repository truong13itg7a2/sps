package edu.txts.sps131025.service.impl;

import edu.txts.sps131025.dto.request.UserD5Create;
import edu.txts.sps131025.dto.request.UserD5Update;
import edu.txts.sps131025.dto.response.UserD5Response;
import edu.txts.sps131025.mapper.UserD5Mapper;
import edu.txts.sps131025.model.UserD5;
import edu.txts.sps131025.repository.UserD5Repository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserD5ServiceImpl {
	UserD5Repository userD5Repository;
	UserD5Mapper userD5Mapper;

	public UserD5Response createUserD5( UserD5Create userD5Create) {
		if (userD5Repository.existsByUsername(userD5Create.getUsername())) {
			throw new RuntimeException("Username already exists");

		}
		UserD5 userD5 = userD5Repository.save(userD5Mapper.toUserD5(userD5Create));
		return userD5Mapper.toUserD5Response(userD5);
	}

	public UserD5Response updateUserD5(String id, UserD5Update userD5Update) {
		UserD5 userD5 = getUserD5ById(id);
		userD5Mapper.updateUserD5(userD5Update, userD5);
		return userD5Mapper.toUserD5Response(userD5);
	}


	public List<UserD5> getAllUserD5s() {
		return userD5Repository.findAll();
	}

	public UserD5 getUserD5ById(String id) {
		return userD5Repository.findById(id).orElseThrow(() -> new RuntimeException("UserD5 not found"));
	}

	public void deleteUserD5ById(String id) {
		userD5Repository.deleteById(id);
	}

	public void deleteAllUserD5s() {
		userD5Repository.deleteAll();
	}

	/*@Override
	public Optional<User> findById(UserId id) {
		return userJpaRepository.findById(id.getValue())
				.map(userEntityMapper::toDomain);
	}
	@Override
	public User save(User user) {
		UserEntity entity = userEntityMapper.toEntity(user);
		UserEntity savedEntity = userJpaRepository.save(entity);
		return userEntityMapper.toDomain(savedEntity);
	}*/
}
