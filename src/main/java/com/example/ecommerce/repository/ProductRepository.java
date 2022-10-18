package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByOrderByProductNameAsc();
    Page<Product> findByCategory(Pageable pageable, Category category);
    @Query(value = "SELECT p FROM Product p " +
            "inner join p.category c " +
            "WHERE LOWER(c.categoryName)=LOWER(?1)")
    Page<Product> findByCategoryName(Pageable pageable, String categoryName);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%',?1,'%')")
    Page<Product> findByProductName(Pageable pageable, String productName);
    @Query(value = "SELECT p FROM Product p " +
            "inner join p.category c " +
            "WHERE c.categoryName=?1 " +
            "OR p.productName=?2")
    Page<Product> findByConditions(Pageable pageable, String categoryName, String productName);

}
