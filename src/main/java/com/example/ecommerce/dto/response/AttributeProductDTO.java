package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeProductDTO {
    private Long attributeProductId;
    private String value;
    private Long productId;
    private Long attributeId;

    public AttributeProductDTO(String value, Long productId, Long attributeId) {
        this.value = value;
        this.productId = productId;
        this.attributeId = attributeId;
    }
}
