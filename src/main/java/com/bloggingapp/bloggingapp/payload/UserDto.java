package com.bloggingapp.bloggingapp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String about;
}