package com.bloggingapp.bloggingapp.service;

import java.util.List;

import com.bloggingapp.bloggingapp.payload.UserDto;

public interface UserService {
    UserDto registerUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
