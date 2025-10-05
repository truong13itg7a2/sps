package edu.txts.spsfdsd1.mapper;

import edu.txts.spsfdsd1.dto.request.UserD2Update;
import edu.txts.spsfdsd1.dto.request.UserD2_Create;
import edu.txts.spsfdsd1.dto.response.UserD2_Response;
import edu.txts.spsfdsd1.entity.UserD2;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserD2_Mapper {
	UserD2 toUserD2(UserD2_Create userD2_create);

	UserD2_Response toUserD2_Response(UserD2 userD2);

	void updateUserD2(UserD2Update userD2Update, @MappingTarget UserD2 userD2);

	List<UserD2_Response> toListUserD2_Response(List<UserD2> userD2List);
}
