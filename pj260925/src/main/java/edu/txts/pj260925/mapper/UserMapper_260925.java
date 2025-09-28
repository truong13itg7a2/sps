package edu.txts.pj260925.mapper;

import edu.txts.pj260925.dto.request.UserCreate_260925v1;
import edu.txts.pj260925.dto.request.UserUpdate_260925v1;
import edu.txts.pj260925.dto.response.UserResponse_260925;
import edu.txts.pj260925.model.User_260925v1;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper_260925 {
	/*Dung khi su dung Java thuan componentModel = "spring" Springboot quan ly bean nay
	UserMapper_260925 INSTANCE = Mappers.getMapper(UserMapper_260925.class);*/
	User_260925v1 toUser_260925v1(UserCreate_260925v1 user);
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUser_260925v1(@MappingTarget User_260925v1 user, UserUpdate_260925v1 update);
//	@Mapping()
	UserResponse_260925 toUserResponse_260925(User_260925v1 user);
}
