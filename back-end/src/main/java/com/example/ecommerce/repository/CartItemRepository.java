package com.example.ecommerce.repository;

import com.example.ecommerce.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartCartIdAndProductProductId(Long cartId, Long productId);
    Page<CartItem> findCartItemByCartCartId(Pageable pageable, Long cartId);
    List<CartItem> findCartItemByCartCartId(Long cartId);
    Optional<CartItem> findByCartCartIdAndCartItemId(Long cartId, Long cartItemId);
    void deleteAllByCartCartId(Long cartId);
}