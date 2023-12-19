package com.bloggingapp.bloggingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bloggingapp.payload.ApiResponse;
import com.bloggingapp.bloggingapp.payload.CommentDto;
import com.bloggingapp.bloggingapp.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // POST mapping to create comment
    @PostMapping("post/{postId}/user/{userId}/comment")
    public ResponseEntity<CommentDto> createComment(
        @RequestBody CommentDto commentDto, 
        @PathVariable("postId") Integer postId, 
        @PathVariable("userId") Integer userId) {
        CommentDto comment = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // PUT mapping to update comment
    @PutMapping("comment/update/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable("commentId") Integer commentId) {
        CommentDto updatedComment = commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // DELETE mapping to delete comment
    @DeleteMapping("delete/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted", true), HttpStatus.OK);
    }
    
    // GET mapping to get all comments in a post
    @GetMapping("post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("postId") Integer postId){
        List<CommentDto> comments = commentService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
