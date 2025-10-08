package edu.txts.pj071025.service;

import edu.txts.pj071025.dto.request.UserD7_Request;
import edu.txts.pj071025.dto.request.UserD7_Update;
import edu.txts.pj071025.dto.response.UserD7_Response;
import edu.txts.pj071025.model.UserD7;

import java.util.List;
import java.util.Optional;

public interface ItfcUserD7_Service {

	UserD7_Response createUser(UserD7_Request userD7_request);
	List<UserD7> getUsers();
	Optional<UserD7> getUserById(String id);
	Optional<UserD7_Response> getUserByUsername(String username);
	UserD7_Response updateUser(String id, UserD7_Update userD7_update);
	void deleteUser(String id);


}
