package com.example.ecommerce.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemPostDTO {
    private Long productId;
    private BigDecimal price;
    private String color;
    private Integer quantity;
}
