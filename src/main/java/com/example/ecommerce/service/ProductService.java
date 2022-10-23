package com.example.ecommerce.service;

import com.example.ecommerce.dto.ListProductDTO;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.ProductPostDTO;
import com.example.ecommerce.dto.ResponseProductDTO;
import com.example.ecommerce.entity.Product;

import java.util.List;


public interface ProductService {
    ResponseProductDTO createProduct(ProductDTO productDTO);
    ProductDTO findById(Long id);
    List<Product> findAll(int pageNumber, int pageSize);
    ListProductDTO findByConditions(int pageNumber, int pageSize, String productName, String categoryName);
    ListProductDTO findByCategoryId(int pageNumber, int pageSize, Long categoryId);
    ResponseProductDTO updatedProductById(ProductDTO productDTO, Long productId);
    void deleteProductById(Long id);
}
