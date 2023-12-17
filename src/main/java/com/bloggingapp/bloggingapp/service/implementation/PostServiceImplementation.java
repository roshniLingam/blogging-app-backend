package com.bloggingapp.bloggingapp.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bloggingapp.bloggingapp.entity.Category;
import com.bloggingapp.bloggingapp.entity.Post;
import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;
import com.bloggingapp.bloggingapp.payload.PostDto;
import com.bloggingapp.bloggingapp.payload.PostResponse;
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
        User user = userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
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
        Post post = postRepo.findById(postId)
                    .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                    .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepo.findAll(pageable);
        List<Post> posts = page.getContent();
        List<PostDto> postsDto = posts.stream()
                                    .map(post -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return PostResponse.builder()
                .posts(postsDto)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                        .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepo.findByCategory(category, pageable);
        List<Post> posts = page.getContent();
        List<PostDto> postsDto = posts.stream()
                                    .map(post -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return PostResponse.builder()
                .posts(postsDto)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        User user = userRepo.findById(userId)
                        .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepo.findByUser(user, pageable);
        List<Post> posts = page.getContent();
        List<PostDto> postsDto = posts.stream()
                                    .map(post -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return PostResponse.builder()
                .posts(postsDto)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
