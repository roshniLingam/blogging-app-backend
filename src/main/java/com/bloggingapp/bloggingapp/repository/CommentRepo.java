package com.bloggingapp.bloggingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.bloggingapp.entity.Comment;
import com.bloggingapp.bloggingapp.entity.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findByPost(Post post);
    
}
