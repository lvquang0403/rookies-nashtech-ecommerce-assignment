package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select distinct on (p.product_name) p.* " +
            "from product p", nativeQuery = true)
    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByOrderByProductNameAsc();
    Optional<Product> findByProductNameIgnoreCase(String productName);
    @Query(value = "SELECT count(p) FROM Product p " +
            "inner join p.category c " +
            "WHERE c.categoryId = ?1")
    Integer countProductByCategoryId(Long categoryId);
    @Query(value = "select distinct on (p.product_name) p.* " +
            "from product p where p.category_id = ?1", nativeQuery = true)
    Page<Product> findByCategoryId(Pageable pageable, Long categoryId);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%',?1,'%')")
    Page<Product> filterByProductName(Pageable pageable, String productName);
    @Query(value = "SELECT p FROM Product p " +
            "inner join p.category c " +
            "WHERE lower(c.categoryName) = lower(?1) " +
            "OR lower(p.productName) like CONCAT('%',lower(?1),'%')")
    Page<Product> filterByConditions(Pageable pageable, String condition);

}
