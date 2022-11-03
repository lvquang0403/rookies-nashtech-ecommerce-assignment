package com.example.ecommerce.service;



import com.example.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category findById(Long categoryId);
    List<Category> findAll(int pageNumber, int pageSize);
    Category updateById(Long categoryId, Category category);
    void deleteById(Long id);
}
