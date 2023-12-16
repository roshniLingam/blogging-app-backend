package com.bloggingapp.bloggingapp.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.bloggingapp.entity.Category;
import com.bloggingapp.bloggingapp.entity.Post;
import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;
import com.bloggingapp.bloggingapp.payload.PostDto;
import com.bloggingapp.bloggingapp.repository.CategoryRepo;
import com.bloggingapp.bloggingapp.repository.PostRepo;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import com.bloggingapp.bloggingapp.service.PostService;

/**
 * Implementation class for PostService
 */
@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName(postDto.getImageName());
        post.setCategory(category);
        post.setUser(user);
        post.setCreateDate(new Date());
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> postsDto = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postsDto = posts.stream()
                                    .map(post -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postsDto = posts.stream()
                                    .map(post -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
