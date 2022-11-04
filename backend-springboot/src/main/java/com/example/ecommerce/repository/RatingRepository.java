package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(value = "SELECT r FROM Rating r " +
            "inner join r.product p " +
            "WHERE p.productId = ?1")
    Page<Rating> findAllByProductId(Pageable pageable, Long productId);
    Optional<Rating> findByProductProductIdAndCustomerCustomerId(Long productId, Long customerId);
    @Query(value = "SELECT AVG (r.score) From Rating r " +
            "inner join r.product p " +
            "WHERE p.productId = ?1 ")
    Double avgRatingByProductId(Long productId);
}
