package com.example.ecommerce.dto.request;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPostDTO {
    private Long productId;
    private Long customerId;
    private String color;
    private Integer quantity;
}
