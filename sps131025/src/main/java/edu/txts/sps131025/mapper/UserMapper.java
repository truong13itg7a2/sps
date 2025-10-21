package edu.txts.sps131025.mapper;

import edu.txts.sps131025.dto.request.UserRequest;
import edu.txts.sps131025.dto.response.UserResponse;
import org.mapstruct.factory.Mappers;

public interface  UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// map từ DTO -> Entity
	User toEntity(UserRequest request);

	// map từ Entity -> DTO
	UserResponse toResponse(User entity);
}
