package com.bloggingapp.bloggingapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min = 2, max = 20, message = "Category name must be of length 3 to 20")
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z_]*$", message = "Category name must not contain spaces and special characters")
    private String categoryTitle;
    private String categoryDescription;
}
