package com.example.ecommerce.repository;

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

    @Query(value = "SELECT ap.value FROM AttributeProduct ap " +
            "inner join ap.product p " +
            "inner join ap.attribute a " +
            "WHERE p.productId = ?1 " +
            "AND lower(a.attributeName) = 'image'")
    String findMainImageProductByProductId(Long productId);
    @Query(value = "SELECT ap.value FROM AttributeProduct ap " +
            "inner join ap.product p " +
            "inner join ap.attribute a " +
            "WHERE p.productId = ?1 " +
            "AND lower(a.attributeName) like '%image%'")
    List<String> findImagesProductByProductId(Long productId);
    @Query(value = "SELECT ap FROM AttributeProduct ap " +
            "INNER JOIN ap.product p "+
            "INNER JOIN ap.attribute a "+
            "WHERE lower(a.attributeName) NOT LIKE   '%image%' " +
            "AND p.productId = ?1")
    List<AttributeProduct> findInfoAttributeWithoutImageByProductId(Long productId);

    @Query(value = "SELECT ap FROM AttributeProduct ap " +
            "INNER JOIN ap.product p "+
            "INNER JOIN ap.attribute a "+
            "WHERE lower(a.attributeName) = lower(?1) " +
            "AND p.productId = ?2")
    Optional<AttributeProduct> findByAttributeAttributeNameAndProductProductIdIgnoreCase(String attributeName, Long productId);

}
