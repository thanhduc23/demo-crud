package com.example.util.mappper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;
import com.example.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    // @Mapping(target = "id", ignore = true)
    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "updatedAt", ignore = true)
    // @Mapping(target = "vehicles", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserDetailResponse toDto(User entity);

    // @Mapping(target = "id", ignore = true)
    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "updatedAt", ignore = true)
    // @Mapping(target = "vehicles", ignore = true)
    void updateEntity(@MappingTarget User entity, UserRequestDTO dto);
}
