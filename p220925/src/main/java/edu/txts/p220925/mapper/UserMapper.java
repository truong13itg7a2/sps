package edu.txts.p220925.mapper;

import edu.txts.p220925.model.User220925;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User220925 user220925(User220925 user);
	void updateUser(@MappingTarget User220925 user, User220925 user2);

	UserResponse toUserResponse(User220925 user220925);
}
