package com.bloggingapp.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
    
}
