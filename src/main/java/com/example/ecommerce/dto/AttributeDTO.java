package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDTO {
    private Long attributeId;
    private String attributeName;
    private String value;
    private String description;

    public AttributeDTO(String attributeName, String value) {
        this.attributeName = attributeName;
        this.value = value;
    }

    public AttributeDTO(Long attributeId, String attributeName, String value) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.value = value;
    }
}
