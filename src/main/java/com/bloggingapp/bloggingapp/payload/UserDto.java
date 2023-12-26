package com.bloggingapp.bloggingapp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * A DTO class for User
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$";
    private Integer userId;
    @NotEmpty
    @NotBlank
    @Size(min = 3, message = "Username must be minimum of length 3")
    private String name;
    
    @Email(message = "Invalid email address")
    private String email;
    
    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 15, message = "Pasword length must be between 6 to 15")
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain atleast 1 Uppercase, 1 Lowercase, 1 Digit, 1 Special character")
    @JsonIgnore
    private String password;
    
    private String about;
}
