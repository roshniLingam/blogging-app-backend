package com.bloggingapp.bloggingapp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.payload.UserDto;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import com.bloggingapp.bloggingapp.service.UserService;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;

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
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepo.save(user);
        return mapToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        return mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(user);
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
