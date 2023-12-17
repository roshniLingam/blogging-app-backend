package com.bloggingapp.bloggingapp.service;

import com.bloggingapp.bloggingapp.payload.PostDto;
import com.bloggingapp.bloggingapp.payload.PostResponse;

public interface PostService {
    // Method to create Post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    // Method to update post
    PostDto updatePost(PostDto postDto, Integer postId);
    // Method to delete post
    void deletePost(Integer postId);
    // Method to get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String dir);
    // Method to get single post
    PostDto getPostById(Integer postId);
    // Method to get post by category
    PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String dir);
    // Method to get post by user
    PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String dir);
    // Method to search post
    PostResponse searchPostByTitle(String keyword, Integer pageNumber, Integer pageSize);
}
