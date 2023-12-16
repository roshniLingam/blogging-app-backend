package com.bloggingapp.bloggingapp.service;

import java.util.List;

import com.bloggingapp.bloggingapp.payload.PostDto;

public interface PostService {
    // Method to create Post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    // Method to update post
    PostDto updatePost(PostDto postDto, Integer postId);
    // Method to delete post
    void deletePost(Integer postId);
    // Method to get all posts
    List<PostDto> getAllPosts();
    // Method to get single post
    PostDto getPostById(Integer postId);
    // Method to get post by category
    List<PostDto> getPostByCategory(Integer categoryId);
    // Method to get post by user
    List<PostDto> getAllPostByUser(Integer userId);
    // Method to search post
    List<PostDto> searchPost(String keyword);
}
