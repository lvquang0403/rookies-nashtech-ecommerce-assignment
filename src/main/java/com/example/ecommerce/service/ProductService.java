package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Page<Product> findAll(int pageNumber, int pageSize);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product updatedProductById(Product product, Long productId);

    Page<Product> findByCategory(int pageNumber, int pageSize, Long categoryId);
}
