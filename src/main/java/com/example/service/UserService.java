package com.example.service;

import com.example.dto.response.PageResponse;
import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;

public interface UserService {
    long saveUser(UserRequestDTO request);

    void updateUser(long userId, UserRequestDTO request);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    PageResponse getAllUsers(int pageNo, int pageSize);
}
