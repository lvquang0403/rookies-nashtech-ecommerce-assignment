package com.example.ecommerce.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemPutDTO {
    private Long cartItemId;
    private Integer quantity;
}
