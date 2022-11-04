package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByColorIgnoreCaseAndProductProductId(String color, Long productId);
    List<Image> findAllByProductProductId(Long productId);
}
