package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    @Query(value = "SELECT a FROM Attribute a " +
            "inner join a.products p " +
            "WHERE p.productId=?1")
    List<Attribute> findByProductId(Long productId);
}
