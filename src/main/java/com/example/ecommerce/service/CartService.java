package com.example.ecommerce.service;

import com.example.ecommerce.dto.response.CartDTO;


public interface CartService {
    CartDTO createCart();
    CartDTO findByCustomerId(Long customerId);
}
