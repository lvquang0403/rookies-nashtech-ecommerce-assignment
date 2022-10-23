package com.example.ecommerce.service.impl;
import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RatingRepositoryAndServiceTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingService ratingService;
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
        ratingService.createRating(new RatingDTO("Quang","good", 2L));
    }

    @Test
    void createProduct_ShouldReturnProductId_WhenInsertSuccessfully() throws Exception {
        assertThrows(NotFoundException.class, () -> ratingService.findById(2L));
    }

}
