package com.bloggingapp.bloggingapp.payload;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Integer postId;
    @NotBlank
    @Size(min = 5, max = 30, message = "Title must be of length 5 to 30")
    private String title;
    @NotBlank
    @Size(min = 5)
    private String content;
    private String imageName;
    private Date createDate;
    private UserDto user;
    private CategoryDto category;
}
