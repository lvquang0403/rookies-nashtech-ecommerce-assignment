package com.example.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPostDTO {
    private Long productId;
    private Long customerId;
    private String color;
    private Integer quantity;
}
