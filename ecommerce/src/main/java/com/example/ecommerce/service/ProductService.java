package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.ProductPostDTO;
import com.example.ecommerce.dto.request.ProductPutDTO;
import com.example.ecommerce.dto.response.DetailProductDTO;
import com.example.ecommerce.dto.response.ListProductViewDTO;
import com.example.ecommerce.dto.response.ResponseProductDTO;
import com.example.ecommerce.entity.Product;

import java.util.List;


public interface ProductService {
    ResponseProductDTO createProduct(ProductPostDTO productDTO);

    DetailProductDTO findById(Long id);

    List<Product> findAll(int pageNumber, int pageSize);

    ListProductViewDTO searchProductByProductName(int pageNumber, int pageSize, String productName);

    ListProductViewDTO findByCategoryId(int pageNumber, int pageSize, Long categoryId);

    void updatedProductById(ProductPutDTO productDTO, Long productId);

    void deleteProductById(Long id);
}
