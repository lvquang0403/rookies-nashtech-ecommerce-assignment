package com.example.ecommerce.service;


import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.ItemPostDTO;
import com.example.ecommerce.dto.ListCartItemDTO;

import java.util.List;

public interface CartItemService {

    ListCartItemDTO findByCustomerId(int pageNumber, int pageSize, Long customerId);
    CartDTO addToCart(ItemPostDTO itemDTO);
    List<ItemPostDTO> updateCartItemByCustomerId(Long customerId , List<ItemPostDTO> itemPostDTOS);
    void deleteById(Long id);

}
