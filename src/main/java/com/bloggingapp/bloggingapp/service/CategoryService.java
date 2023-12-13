package com.bloggingapp.bloggingapp.service;

import java.util.List;

import com.bloggingapp.bloggingapp.payload.CategoryDto;

public interface CategoryService {
    // Method to create new category
    CategoryDto createCategory(CategoryDto categoryDto);
    // Method to update existing category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    // Method to delete a category
    void deleteCategory(Integer categoryId);
    // Method to get a category by its Id
    CategoryDto getCategory(Integer categoryId);
    // Method to get all categories
    List<CategoryDto> getAllCategory();
}
