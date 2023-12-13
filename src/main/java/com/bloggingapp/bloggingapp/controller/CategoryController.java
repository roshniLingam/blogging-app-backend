package com.bloggingapp.bloggingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bloggingapp.payload.ApiResponse;
import com.bloggingapp.bloggingapp.payload.CategoryDto;
import com.bloggingapp.bloggingapp.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Controller class for Categories
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * POST mapping to create category
     * @param categoryDto
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * PUT mapping to update category
     * @param categoryDto
     * @param categoryId
     * @return ResponseEntity
     */
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId) {
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(category);
    }

    /**
     * DELETE mapping to delete category by Id
     * @param categoryId
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted", true), HttpStatus.OK);
    }

    /**
     * GET mapping to get category by Id
     * @param categoryId
     * @return ResponseEntity
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    /**
     * GET mapping to get all categories
     * @return ResponseEntity with category list
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}