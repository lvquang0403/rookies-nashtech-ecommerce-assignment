package com.example.ecommerce.service;


import com.example.ecommerce.dto.request.CartItemPutDTO;
import com.example.ecommerce.dto.response.CartDTO;
import com.example.ecommerce.dto.request.ItemPostDTO;
import com.example.ecommerce.dto.response.ListCartItemDTO;

import java.util.List;

public interface CartItemService {
    ListCartItemDTO findByCustomerId(int pageNumber, int pageSize);
    CartDTO addToCart(ItemPostDTO itemDTO);
    void updateCartItem(CartItemPutDTO itemPostDTO);
    void deleteById(Long id);

}
