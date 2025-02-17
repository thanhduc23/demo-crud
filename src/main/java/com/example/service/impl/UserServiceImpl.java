package com.example.service.impl;

import com.example.dto.response.PageResponse;
import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// TODO : dùng MapStruct thay builder
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public long saveUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
    
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public void updateUser(long userId, UserRequestDTO request) {
        User user = getUserById(userId);
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return UserDetailResponse.builder()
                .id(userId)
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public PageResponse getAllUsers(int pageNo, int pageSize) {
        Page<User> page = userRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<UserDetailResponse> list = page.stream().map(user -> UserDetailResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build()
        ).toList();
        return PageResponse.builder()
        .pageNo(pageNo)
        .pageSize(pageSize)
        .totalPage(page.getTotalPages())
        .items(list)
        .build();
    }

    private User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Get user failed"));
    }
}
