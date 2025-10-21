package edu.txts.sps131025.mapper;

import edu.txts.sps131025.dto.request.UserD5Create;
import edu.txts.sps131025.dto.request.UserD5Update;
import edu.txts.sps131025.dto.response.UserD5Response;
import edu.txts.sps131025.entity.UserD5;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserD5Mapper {
	UserD5 toUserD5(UserD5Create userD5Create);
	UserD5Response toUserD5Response(UserD5 userD5);

	void updateUserD5(UserD5Update userD5Update, @MappingTarget UserD5 userD5);
}
