package edu.txts.pj260925.service;

import edu.txts.pj260925.dto.request.UserCreate_260925v1;
import edu.txts.pj260925.dto.request.UserUpdate_260925v1;
import edu.txts.pj260925.model.User_260925v1;
import edu.txts.pj260925.repository.UserRepo_260925v1;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImplt_260925v1 implements Itfc_UserService_260925v1{
	UserRepo_260925v1 userRepo;
	@Override
	public User_260925v1 create_user(UserCreate_260925v1 userCreate) {
		if (userRepo.existsByUsername(userCreate.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		User_260925v1 user = new User_260925v1();
		user.setUsername(userCreate.getUsername());
		user.setPassword(userCreate.getPassword());
		user.setFirstName(userCreate.getFirstName());
		user.setLastName(userCreate.getLastName());
		user.setBirthday(userCreate.getBirthday());
		return userRepo.save(user);
	}

	@Override
	public List<User_260925v1> get_users() {
		return userRepo.findAll();
	}

	@Override
	public User_260925v1 get_user(String id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
	}

	@Override
	public User_260925v1 update_user(String id, UserUpdate_260925v1 userUpdate) {
		User_260925v1 user = get_user(id);
		user.setPassword(userUpdate.getPassword());
		user.setFirstName(userUpdate.getFirstName());
		user.setLastName(userUpdate.getLastName());
		user.setBirthday(userUpdate.getBirthday());
		return userRepo.save(user);
	}

	@Override
	public void delete_user(String id) {
		userRepo.deleteById(id);
	}
}
