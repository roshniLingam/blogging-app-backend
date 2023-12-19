package com.bloggingapp.bloggingapp.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.bloggingapp.entity.Comment;
import com.bloggingapp.bloggingapp.entity.Post;
import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;
import com.bloggingapp.bloggingapp.payload.CommentDto;
import com.bloggingapp.bloggingapp.repository.CommentRepo;
import com.bloggingapp.bloggingapp.repository.PostRepo;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import com.bloggingapp.bloggingapp.service.CommentService;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatDate(new Date());
        Comment newComment = commentRepo.save(comment);
        return modelMapper.map(newComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                            .orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setUserComment(commentDto.getUserComment());
        Comment updatedComment = commentRepo.save(comment);
        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                            .orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getAllComments(Integer postId) {
        Post post = postRepo.findById(postId)
                        .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        List<Comment> comments = commentRepo.findByPost(post);
        List<CommentDto> commentsDto = comments.stream()
                                        .map(comment -> modelMapper.map(comment, CommentDto.class))
                                        .collect(Collectors.toList());
        return commentsDto;
    }
    
}
