package edu.txts.spsfdsd1.service.itfc;

import edu.txts.spsfdsd1.dto.request.UserD1_Create;
import edu.txts.spsfdsd1.dto.request.UserD1_Update;
import edu.txts.spsfdsd1.dto.response.UserD1_Response;

import java.util.List;

public interface UserD1_Service {
	UserD1_Response creatUserD1(UserD1_Create userD1_Create);
	UserD1_Response getUserD1(String id);
	List<UserD1_Response> getAllUserD1();
	UserD1_Response updateUserD1(String id, UserD1_Update userD1_Update);
	void deleteUserD1(String id);

}
