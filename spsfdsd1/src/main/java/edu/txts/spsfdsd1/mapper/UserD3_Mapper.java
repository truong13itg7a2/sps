package edu.txts.spsfdsd1.mapper;

import edu.txts.spsfdsd1.dto.request.UserD3_Request_Create;
import edu.txts.spsfdsd1.dto.request.UserD3_Request_Update;
import edu.txts.spsfdsd1.dto.response.UserD3_Response;
import edu.txts.spsfdsd1.entity.UserD3;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserD3_Mapper {
	UserD3_Response toUserD3_Response(Optional<UserD3> userD3);

	UserD3_Response toUserD3_Response(UserD3 userD3);

	List<UserD3_Response> toListUserD3_Response(List<UserD3> userD3);

	UserD3_Response toUserD3_ResponseFURC(UserD3_Request_Create userD3_Request_Create);

	UserD3 toUserD3(UserD3_Request_Create userD3_Request_Create);

	UserD3 toUserD3(UserD3_Response userD3_Response);

	void updateUserD3(@MappingTarget UserD3 userD3, UserD3_Request_Update userD3_request_update);
}
