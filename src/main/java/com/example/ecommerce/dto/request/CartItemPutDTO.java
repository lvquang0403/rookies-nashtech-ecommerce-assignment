package com.example.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemPutDTO {
    private Long cartItemId;
    private Integer quantity;
}
