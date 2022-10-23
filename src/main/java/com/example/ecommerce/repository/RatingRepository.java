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
    @Query(value = "SELECT r FROM Rating r " +
            "inner join r.product p " +
            "inner join r.customer c " +
            "WHERE p.productId = ?1 " +
            "AND LOWER(c.userName) = LOWER(?2)")
    Optional<Rating> findByProductIdAndUserName(Long productId, String userName);
}
