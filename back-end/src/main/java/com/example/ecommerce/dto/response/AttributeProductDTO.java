package com.example.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
