package com.example.ecommerce.controller;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<List<Category>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ) {
        return ResponseEntity.ok(categoryService.findAll(pageNumber, pageSize));
    }

    @PutMapping("{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.updateById(categoryId, category));
    }

    @DeleteMapping("{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteById(categoryId);
        return ResponseEntity.ok("successfully");
    }




}
