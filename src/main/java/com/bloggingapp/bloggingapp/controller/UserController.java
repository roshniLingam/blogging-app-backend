package com.bloggingapp.bloggingapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bloggingapp.payload.ApiResponse;
import com.bloggingapp.bloggingapp.payload.UserDto;
import com.bloggingapp.bloggingapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * POST mapping to create user
     * @param userDto
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * PUT mapping to update user
     * @param userDto UserDto
     * @param userId Integer
     * @return ResponseEntity
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * DELETE mapping to delete user
     * @param userId
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted", true), HttpStatus.OK);
    }

    /**
     * GET mapping to get user by Id
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
