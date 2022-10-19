package com.example.ecommerce.controller;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.response.ResponseObject;
import com.example.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("createCategory")
    @ResponseBody
    ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("categorys")
    @ResponseBody
    ResponseEntity<List<Category>> getCategorys(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ) {
        return ResponseEntity.ok(categoryService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/getById/{categoryId}")
    @ResponseBody
    ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @PutMapping("updateCategory/{categoryId}")
    @ResponseBody
    ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.updateById(categoryId, category));
    }

    @DeleteMapping("deleteCategory/{categoryId}")
    ResponseEntity<ResponseObject> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteById(categoryId);
        return ResponseEntity.ok(new ResponseObject("200", "successfully", null));
    }




}
