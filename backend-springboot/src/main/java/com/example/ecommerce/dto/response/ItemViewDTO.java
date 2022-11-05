package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.OrderItem;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemViewDTO {
    private Long id;
    private String image;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private String color;
    private BigDecimal totalPrice;
    private Long productId;
}
