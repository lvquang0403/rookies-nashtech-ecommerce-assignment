package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDTO;


public interface CartService {
    CartDTO createCart();
    CartDTO findByCustomerId(Long customerId);
}
