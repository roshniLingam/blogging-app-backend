package com.bloggingapp.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
