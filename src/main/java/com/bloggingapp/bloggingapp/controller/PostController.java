package com.bloggingapp.bloggingapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bloggingapp.payload.ApiResponse;
import com.bloggingapp.bloggingapp.payload.PostDto;
import com.bloggingapp.bloggingapp.payload.PostResponse;
import com.bloggingapp.bloggingapp.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    //POST mapping to create post
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(
        @Valid @RequestBody PostDto postDto,
        @PathVariable("userId") Integer userId, 
        @PathVariable("categoryId") Integer categoryId) {
        PostDto post = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    
    //GET mapping get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(
        @PathVariable("userId") Integer userId,
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "createDate", required = false) String sortBy,
        @RequestParam(value = "dir", defaultValue = "desc", required = false) String dir) {
        PostResponse postResponse = postService.getAllPostByUser(userId, pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //GET mapping to get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(
        @PathVariable("categoryId") Integer categoryId,
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "createDate", required = false) String sortBy,
        @RequestParam(value = "dir", defaultValue = "desc", required = false) String dir) {
        PostResponse postResponse = postService.getPostByCategory(categoryId, pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //GET mapping to get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "createDate", required = false) String sortBy,
        @RequestParam(value = "dir", defaultValue = "desc", required = false) String dir) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //GET mapping to get single post by Id
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //DELETE mapping to delete a post by Id
    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted", true), HttpStatus.OK);
    }

    //PUT mapping to update post by Id
    @PutMapping("/update/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") Integer postId){
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // search post
}