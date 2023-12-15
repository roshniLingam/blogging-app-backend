package com.bloggingapp.bloggingapp.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String imageName;
    private Date createDate;
    private UserDto user;
    private CategoryDto category;
}
