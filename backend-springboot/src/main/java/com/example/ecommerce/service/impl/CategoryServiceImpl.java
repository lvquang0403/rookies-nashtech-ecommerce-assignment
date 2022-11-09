package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.StillRelationException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category createCategory(Category category) {
        String categoryName = category.getCategoryName();
        if (categoryRepository.findByCategoryNameIgnoreCase(categoryName).isPresent()) {
            throw new DuplicateException(String.format("Category with name %s already exists", categoryName));
        }
        return categoryRepository.save(Category.builder().categoryName(categoryName).build());
    }

    @Override
    public List<Category> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryId"));
        return categoryRepository.findAll(pageable).getContent();
    }


    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id %s is not found", categoryId)));
    }

    @Override
    public Category updateById(Long categoryId, Category category) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id %s is not found", categoryId)));

        if (!category.getCategoryName().isEmpty()) {
            foundCategory.setCategoryName(category.getCategoryName());
        }
        return categoryRepository.save(foundCategory);
    }

    @Override
    public void deleteById(Long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id %s is not found", categoryId)));
        if (productRepository.countProductByCategoryId(categoryId) > 0) {
            throw new StillRelationException(String.format("Category  still referent to another Product", categoryId));
        } else {
            categoryRepository.deleteById(foundCategory.getCategoryId());
        }
    }
}
