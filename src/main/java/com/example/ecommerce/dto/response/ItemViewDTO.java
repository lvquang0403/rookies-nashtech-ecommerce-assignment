package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemViewDTO {
    private Long cartItemId;
    private String image;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;

    public ItemViewDTO(Long cartItemId, String productName, BigDecimal price, Integer quantity, BigDecimal totalPrice) {
        this.cartItemId = cartItemId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
