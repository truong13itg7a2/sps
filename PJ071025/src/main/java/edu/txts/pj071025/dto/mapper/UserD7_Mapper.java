package edu.txts.pj071025.dto.mapper;

import edu.txts.pj071025.dto.request.UserD7_Request;
import edu.txts.pj071025.dto.request.UserD7_Update;
import edu.txts.pj071025.dto.response.UserD7_Response;
import edu.txts.pj071025.model.UserD7;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserD7_Mapper {
	UserD7_Response toUserD7_Response(UserD7 userD7);
	UserD7 toUserD7(UserD7_Request userD7Request);
	void updateUserD7(@MappingTarget Optional<UserD7> userD7, UserD7_Update userD7_update);
}
