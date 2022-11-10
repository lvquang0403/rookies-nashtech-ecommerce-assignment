package com.example.ecommerce.dto.request;

import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.AttributeProduct;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDTO {
    @NotNull(message = "attributeId cannot be empty")
    private Long attributeId;
    private String attributeName;
    @NotBlank(message = "value cannot be empty")
    private String value;

    public AttributeDTO(Long attributeId, String attributeName) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
    }

    public static AttributeDTO fromAttributeProduct(AttributeProduct attribute){
        return new AttributeDTO(
                attribute.getAttribute().getAttributeId(),
                attribute.getAttribute().getAttributeName(),
                attribute.getValue()
        );
    }

    public static AttributeDTO fromAttribute(Attribute attribute){
        return new AttributeDTO(
                attribute.getAttributeId(),
                attribute.getAttributeName()
        );
    }


}
