package com.example.ecommerce.service;


import com.example.ecommerce.dto.request.CartItemPutDTO;
import com.example.ecommerce.dto.response.CartDTO;
import com.example.ecommerce.dto.request.ItemPostDTO;
import com.example.ecommerce.dto.response.ListCartItemDTO;

public interface CartItemService {
    ListCartItemDTO findByCustomerId(int pageNumber, int pageSize, Long customerId);
    void addToCart(ItemPostDTO itemDTO);
    Integer getNumberCartItemsByCustomerId(Long customerId);
    void updateCartItem(CartItemPutDTO itemPostDTO);
    void deleteById(Long id);

}
