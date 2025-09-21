package edu.txts.pj200925.service;

import edu.txts.pj200925.model.User;
import edu.txts.pj200925.repository.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
//Su dung contructor bat buoc
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserService {
	@Autowired
	UserRepo userRepo;
	public List<User> getAllUser(){
		return userRepo.findAll();
	}

	public User getUserById(long id){
		return userRepo.findById(id).get();
	}

	public User addUser(User user){
		return userRepo.save(user);
	}

	public User updateUser(long id, User user){
		User u = userRepo.findById(id).get();
		u.setName(user.getName());
		u.setAge(user.getAge());
		u.setIncome(user.getIncome());
		return userRepo.save(user);
	}

	public void deleteUser(long id){
		userRepo.deleteById(id);
	}
}
