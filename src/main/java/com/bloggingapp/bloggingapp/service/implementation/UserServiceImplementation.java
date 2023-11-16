package com.bloggingapp.bloggingapp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.payload.UserDto;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import com.bloggingapp.bloggingapp.service.UserService;

public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToUser(userDto);
        User savedUser = userRepo.save(user);
        return mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {
        // TODO Auto-generated method stub
    }

    // Private method to map UserDto to User
    private User mapToUser(UserDto userDto) {
        return User.builder()
        .id(userDto.getId())
        .name(userDto.getName())
        .email(userDto.getEmail())
        .password(userDto.getPassword())
        .about(userDto.getAbout())
        .build();
    }

    // Private method to map User to UserDto
    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .password(user.getPassword())
        .about(user.getAbout())
        .build();
    }
}
