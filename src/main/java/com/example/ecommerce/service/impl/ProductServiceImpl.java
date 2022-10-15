package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(int pageNumber,int pageSize) {
        log.debug("Request to findAll Product (Paging)");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        log.debug("Request to findAll Product");
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.debug("Request to find Product by id : {}", id);
        return Optional.empty();
    }

    @Override
    public Product updatedProductById(Product product, Long productId) {
        log.debug("Request to update Product : {}", product);
        Product foundProduct = productRepository
                .findById(product.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(String.format("Product with id : %s is not found", product.getProductId())));
        return productRepository.save(foundProduct);
    }

    @Override
    public Page<Product> findByCategory(int pageNumber, int pageSize, Long categoryId) {
        log.debug("Request to find by categoryId Product (Paging)");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryId(pageable);
    }

    public void deleteProductById(Long id) {
        log.debug("Request to delete Product : {}", id);
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(foundProduct.getProductId());
    }
}
