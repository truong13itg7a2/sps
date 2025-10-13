package edu.txts.spsfdsd1.mapper;

import edu.txts.spsfdsd1.dto.request.UserD4Create;
import edu.txts.spsfdsd1.dto.request.UserD4Update;
import edu.txts.spsfdsd1.dto.response.UserD4Response;
import edu.txts.spsfdsd1.entity.UserD4;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserD4Mapper {
	UserD4 toUserD4(UserD4Create userD4Create);

	UserD4Response toUserD4Response(UserD4 userD4);


	void updateUserD4(UserD4Update userD4Update, @MappingTarget UserD4 userD4);

	UserD4 toUserD4(UserD4Update userD4Update);

	UserD4 toUserD4(UserD4Response userD4Response);
}
