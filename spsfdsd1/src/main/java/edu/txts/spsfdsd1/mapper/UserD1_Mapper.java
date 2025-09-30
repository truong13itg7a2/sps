package edu.txts.spsfdsd1.mapper;

import edu.txts.spsfdsd1.dto.request.UserD1_Create;
import edu.txts.spsfdsd1.dto.request.UserD1_Update;
import edu.txts.spsfdsd1.dto.response.UserD1_Response;
import edu.txts.spsfdsd1.entity.UserD1;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserD1_Mapper {
	UserD1_Response toUserD1_Response(UserD1_Create userD1Create);
	UserD1_Response toUserD1_Response(UserD1 userD1);
	UserD1 toUserD1(UserD1_Create userD1Create);
	UserD1 toUserD1(UserD1_Response userD1Response);
	void updateUserD1_Response(UserD1_Update userD1Update, @MappingTarget UserD1_Response userD1_Response);
	List<UserD1_Response> toUserD1_Response(List<UserD1> userD1List);
	List<UserD1> toListUserD1(List<UserD1_Response> userD1ResponseList);
}
