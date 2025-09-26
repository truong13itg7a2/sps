package edu.txts.pj260925.service;

import edu.txts.pj260925.dto.request.UserRequest_260925;
import edu.txts.pj260925.model.User_260925;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Itfc_UserService_260925 {
	User_260925 addUser(UserRequest_260925 user);
	User_260925 getUser(String id);
	List<User_260925> getAllUsers();
	User_260925 updateUser(String id, UserRequest_260925 user);
	void deleteUser(String id);


}
