package com.bloggingapp.bloggingapp.service;

import java.util.List;

import com.bloggingapp.bloggingapp.payload.CommentDto;

public interface CommentService {
    // Method to create comment
    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
    // Method to update comment
    CommentDto updateComment(CommentDto commentDto, Integer commentId);
    // Method to delete comment
    void deleteComment(Integer commentId);
    // Method to get all comments
    List<CommentDto> getAllComments(Integer postId);
}
