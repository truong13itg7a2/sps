package edu.txts.pj260925.service;

import edu.txts.pj260925.dto.request.UserRequest_260925;
import edu.txts.pj260925.model.User_260925;
import edu.txts.pj260925.repository.UserRepo_260925;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplt_260925 implements Itfc_UserService_260925{
	@Autowired
	UserRepo_260925 userRepo;
	@Override
	public User_260925 addUser(UserRequest_260925 userRequest) {
		boolean findUsername = userRepo.existsById(userRequest.getUsername());
		if (!findUsername) {
			User_260925 oUser = new User_260925();
			oUser.setUsername(userRequest.getUsername());
			oUser.setPassword(userRequest.getPassword());
			oUser.setFirstName(userRequest.getFirstName());
			oUser.setLastName(userRequest.getLastName());
			oUser.setBirthDate(userRequest.getBirthDate());
			return userRepo.save(oUser);
		}
		return null;
	}

	@Override
	public User_260925 getUser(String id) {
//		if(userRepo.findByIdContainingIgnoreCase(id) != null){
//
//		}
		return userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
	}

	@Override
	public List<User_260925> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User_260925 updateUser(String id, UserRequest_260925 userRequest) {

		User_260925 oUser = getUser(id);
		if(oUser != null){
			oUser.setUsername(userRequest.getUsername());
			oUser.setPassword(userRequest.getPassword());
			oUser.setFirstName(userRequest.getFirstName());
			oUser.setLastName(userRequest.getLastName());
			oUser.setBirthDate(userRequest.getBirthDate());
			return userRepo.save(oUser);
		}

		return oUser;
	}

	@Override
	public void deleteUser(String id) {
		userRepo.deleteById(id);
	}
}
