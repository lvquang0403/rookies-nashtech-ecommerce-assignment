package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Rating;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    Product product;
    @BeforeEach
    void before(){
        System.out.println(productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 256 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(1000))
                .build()));
        System.out.println(productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 128 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(800))
                .build()));
        productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 512 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(1200))
                .build());
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }
    @Test
    void createProduct_ShouldReturnProductId_WhenInsertSuccessfully() {
        System.out.println(productRepository.save(product));
        Optional<Product> foundProduct = productRepository.findById(1L);
        foundProduct.ifPresent(value -> assertThat(value.getProductId(), is(product.getProductId())));
    }

    @Test
    void updateProduct_ShouldReturnProductName_WhenUpdateSuccessfully() {
        productRepository.save(product);
        product.setProductName("example");
        productRepository.save(product);
        Optional<Product> foundProduct = productRepository.findById(product.getProductId());
        foundProduct.ifPresent(value -> assertThat(value.getProductName(), is(product.getProductName())));
    }


}