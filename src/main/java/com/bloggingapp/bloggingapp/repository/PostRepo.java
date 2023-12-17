package com.bloggingapp.bloggingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.Category;
import com.bloggingapp.bloggingapp.entity.Post;
import com.bloggingapp.bloggingapp.entity.User;


public interface PostRepo extends JpaRepository<Post, Integer> {
    Page<Post> findByCategory(Category category, Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
