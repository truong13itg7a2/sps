package edu.txts.pj260925.service;

import edu.txts.pj260925.dto.request.UserCreate_260925v1;
import edu.txts.pj260925.dto.request.UserUpdate_260925v1;
import edu.txts.pj260925.model.User_260925v1;

import java.util.List;

public interface Itfc_UserService_260925v1 {
	User_260925v1 create_user(UserCreate_260925v1 userCreate);
	List<User_260925v1> get_users();
	User_260925v1 get_user(String id);
	User_260925v1 update_user(String id, UserUpdate_260925v1 userUpdate);
	void delete_user(String id);
}
