package com.example.ecommerce.repository;

import com.example.ecommerce.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartCartIdAndProductProductIdAndColor(Long cartId, Long productId, String color);
    Page<CartItem> findCartItemByCartCartId(Pageable pageable, Long cartId);
    Page<CartItem> findCartItemByCartCustomerCustomerId(Pageable pageable,Long customerId);
    Optional<CartItem> findByCartCartIdAndCartItemId(Long cartId, Long cartItemId);
    @Query(value = "SELECT SUM (c.quantity) from CartItem c where c.cart.customer.customerId = ?1")
    Integer sumByCustomerId(Long customerId);
    void deleteAllByCartCustomerCustomerId(Long customerId);
}
