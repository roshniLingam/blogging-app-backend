package com.bloggingapp.bloggingapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bloggingapp.payload.ApiResponse;
import com.bloggingapp.bloggingapp.payload.PostDto;
import com.bloggingapp.bloggingapp.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
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


@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    //POST mapping to create post
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
        PostDto post = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    
    //GET mapping get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId) {
        List<PostDto> posts = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //GET mapping to get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //GET mapping to get all post
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
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
