package com.bloggingapp.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
}
