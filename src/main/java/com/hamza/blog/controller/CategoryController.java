package com.hamza.blog.controller;

import com.hamza.blog.payload.CategoryDto;
import com.hamza.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build add category REST API

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory (@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    //Build get Category based on id

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory (@PathVariable("id") long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);

       return ResponseEntity.ok(categoryDto);
    }

    // Build All Category REST API

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory () {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Build update category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody CategoryDto categoryDto,
                                                       @PathVariable long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto,categoryId));
    }

    // Build Delete Category
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory (@PathVariable("id") long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully !");
    }
}
