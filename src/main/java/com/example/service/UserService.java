package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;
import com.example.model.User;

public interface UserService {

    User saveUser(UserRequestDTO request);

    void updateUser(long userId, UserRequestDTO request);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    Page<UserDetailResponse> getAllUsers(Pageable pageable);
}
