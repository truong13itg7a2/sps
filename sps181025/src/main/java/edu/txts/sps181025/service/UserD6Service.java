package edu.txts.sps181025.service;

import edu.txts.sps181025.dto.request.UserD6Create;
import edu.txts.sps181025.dto.request.UserD6Update;
import edu.txts.sps181025.dto.response.UserD6Response;
import edu.txts.sps181025.model.UserD6;

import java.util.List;

public interface UserD6Service {
	UserD6Response createUserD6(UserD6Create userD6Create);
	UserD6 getUserD6ById(Long id);
	UserD6 getUserD6ByUsername(String username);
	List<UserD6> getAllUserD6();
	UserD6Response updateUserD6(UserD6Update userD6Update);
	void deleteUserD6(Long id);
}
