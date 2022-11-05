package com.example.ecommerce.repository;

import com.example.ecommerce.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartCartIdAndProductProductId(Long cartId, Long productId);
    Page<CartItem> findCartItemByCartCartId(Pageable pageable, Long cartId);
    List<CartItem> findCartItemByCartCartId(Long cartId);
    Optional<CartItem> findByCartCartIdAndCartItemId(Long cartId, Long cartItemId);
    @Query(value = "SELECT SUM (c.quantity) from CartItem c where c.cart.cartId = ?1")
    Integer sumByCartId(Long cartId);
    void deleteAllByCartCartId(Long cartId);
}
