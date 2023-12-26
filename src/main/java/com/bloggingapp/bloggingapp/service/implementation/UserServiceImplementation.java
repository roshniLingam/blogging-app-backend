package com.bloggingapp.bloggingapp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggingapp.bloggingapp.config.Constants;
import com.bloggingapp.bloggingapp.entity.Role;
import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.payload.UserDto;
import com.bloggingapp.bloggingapp.repository.RoleRepo;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import com.bloggingapp.bloggingapp.service.UserService;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

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

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapToUser(userDto);
        // Encode password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Fetch role Id for non-admin role
        Role role = roleRepo.findById(Constants.NORMAL_USER).get();
        user.getRoles().add(role);
        User registeredUser = userRepo.save(user);
        return mapToUserDto(registeredUser);
    }

    // Private method to map UserDto to User
    private User mapToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    // Private method to map User to UserDto
    private UserDto mapToUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }   
}
