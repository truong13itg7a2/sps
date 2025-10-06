package edu.txts.spsfdsd1.service.itfc;

import edu.txts.spsfdsd1.dto.request.UserD3_Request_Create;
import edu.txts.spsfdsd1.dto.request.UserD3_Request_Update;
import edu.txts.spsfdsd1.dto.response.UserD3_Response;
import edu.txts.spsfdsd1.entity.UserD3;

import java.util.List;

public interface Itfc_UserD3_Service {
	UserD3_Response createUserD3(UserD3_Request_Create userD3_Request_Create);

	UserD3_Response getUserD3(String userId);

	List<UserD3_Response> getAllUserD3v1();

	List<UserD3> getAllUserD3v2();

	UserD3_Response updateUserD3(String userId, UserD3_Request_Update userD3_Request_Update);

	void deleteUserD3(String userId);
}
