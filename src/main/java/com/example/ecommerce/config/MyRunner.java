package com.example.ecommerce.config;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.RatingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class MyRunner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RatingService ratingService;
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    private final RatingRepository ratingRepository;

    public MyRunner(ProductRepository productRepository, CategoryRepository categoryRepository, RatingService ratingService, ProductService productService, CustomerRepository customerRepository, RoleRepository roleRepository, RatingRepository ratingRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ratingService = ratingService;
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.ratingRepository = ratingRepository;
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
                .productName("Macbook Pro 15 20222")
                .description("new 100%")
                .price(BigDecimal.valueOf(2000))
                .category(categoryRepository.save(Category.builder().categoryName("Macbook").build()))
                .build());
        //insert Rating
        customerRepository.save(Customer.builder().userName("Quang")
                        .address("binh duong")
                .build());
        customerRepository.save(Customer.builder().userName("Vinh")
                .address("binh duong")
                .build());
        roleRepository.save(Role.builder().roleName("ROLE_USER").build());
        roleRepository.save(Role.builder().roleName("ROLE_ADMIN").build());
//        ratingService.createRating(new RatingDTO("Quang","good",4, 2L));
//        ratingService.createRating(new RatingDTO("Quang","good dd",4, 3L));


    }
}
