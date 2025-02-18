package com.example.controller;

import com.example.dto.response.PageResponse;
import com.example.dto.response.ResponseData;
import com.example.dto.response.UserDetailResponse;
import com.example.dto.resquest.UserRequestDTO;
import com.example.model.User;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/")
    public ResponseData<User> addUser(@RequestBody UserRequestDTO user) {

        User userId = userService.saveUser(user);

        return new ResponseData<>(HttpStatus.CREATED.value(), "User created successfully", userId);
    }

    @GetMapping("/")
    public ResponseData<PageResponse> getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PageResponse<?> users = userService.getAllUsers(pageNo, pageSize);
        return new ResponseData<>(HttpStatus.OK.value(), "users", users);
    }

    @DeleteMapping("/{userId}")
    public ResponseData<Void> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete successflly");
    }

    @GetMapping("/{userId}")
    public ResponseData<UserDetailResponse> getUserDetails(@PathVariable long userId) {
        UserDetailResponse user = userService.getUser(userId);
        return new ResponseData<>(HttpStatus.OK.value(), "user", user);
    }

    @PutMapping("/{userId}")
    public ResponseData<Void> updateUser(@PathVariable long userId, @RequestBody UserRequestDTO request) {
        userService.updateUser(userId, request);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update User successfully");
    }

}
