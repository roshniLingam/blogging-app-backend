package com.bloggingapp.bloggingapp.payload;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private String userComment;
    private Date createDate;
    private UserDto user;
    private PostDto post;
}
