package com.example.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;
import com.example.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDetailResponse, UserRequestDTO> {

    @Override
    UserDetailResponse toDto(User entity);

    @Override
    User toEntity(UserRequestDTO request);

    @Override
    void updateEntity(@MappingTarget User entity, UserRequestDTO request);
}
