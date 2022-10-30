package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.AttributeProduct;
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

    public AttributeDTO(Long attributeId, String attributeName) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
    }

    public static AttributeDTO fromAttribute(AttributeProduct attribute){
        return new AttributeDTO(
                attribute.getAttribute().getAttributeId(),
                attribute.getAttribute().getAttributeName(),
                attribute.getValue()
        );
    }
}
