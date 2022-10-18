package com.example.ecommerce.service;

import com.example.ecommerce.dto.ListProductDTO;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.ProductPostDTO;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product createProduct(ProductPostDTO productDTO);
    ProductDTO findById(Long id);
    Page<Product> findAll(int pageNumber, int pageSize);
    ListProductDTO findByConditions(int pageNumber, int pageSize, String productName, String typeName);
    ListProductDTO findByCategoryId(int pageNumber, int pageSize, Long categoryId);
    Product updatedProductById(ProductDTO productDTO, Long productId);
    List<ProductDTO> convertToProductDTO(List<Product> products);
    void deleteProductById(Long id);
}
