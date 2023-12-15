package com.bloggingapp.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.Category;
import com.bloggingapp.bloggingapp.entity.Post;
import com.bloggingapp.bloggingapp.entity.User;

import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
}
