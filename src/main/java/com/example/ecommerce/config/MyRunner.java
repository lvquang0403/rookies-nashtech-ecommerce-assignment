package com.example.ecommerce.config;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class MyRunner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public MyRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 256 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(1000))
                .category(categoryRepository.save(Category.builder().categoryName("Iphone").build()))
                .build()));
        System.out.println(productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 128 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(800))
                .category(categoryRepository.save(Category.builder().categoryName("Iphone").build()))
                .build()));
        productRepository.save(Product.builder()
                .productName("Iphone 14 Promax 512 GB")
                .description("new 100%")
                .price(BigDecimal.valueOf(1200))
                .category(categoryRepository.save(Category.builder().categoryName("Iphone").build()))
                .build());
        productRepository.save(Product.builder()
                    .productName("Macbook Pro 15 2022")
                .description("new 100%")
                .price(BigDecimal.valueOf(2400))
                .category(categoryRepository.save(Category.builder().categoryName("Macbook").build()))
                .build());
        productRepository.save(Product.builder()
                .productName("Macbook Pro 15 2021")
                .description("new 100%")
                .price(BigDecimal.valueOf(2000))
                .category(categoryRepository.save(Category.builder().categoryName("Macbook").build()))
                .build());
    }
}
