package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class CreateOrderResponse {
    private String orderName;
    private String customerName;
    private String orderPhone;
    private String address;
    private BigDecimal totalPrice;
    private List<ItemViewDTO> item;
}
