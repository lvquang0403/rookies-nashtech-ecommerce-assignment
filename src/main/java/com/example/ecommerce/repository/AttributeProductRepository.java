package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.AttributeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttributeProductRepository extends JpaRepository<AttributeProduct, Long> {
    @Query(value = "SELECT ap FROM AttributeProduct ap " +
            "inner join ap.product p " +
            "inner join ap.attribute a " +
            "WHERE p.productId = ?1 " +
            "AND a.attributeId = ?2 ")
    Optional<AttributeProduct> findByProductIdAndAttributeId(Long productId, Long attributeId);
    List<AttributeProduct> findAllByProductProductId(Long productId);
    Integer countAttributeProductByAttributeAttributeId(Long attributeId);
}
