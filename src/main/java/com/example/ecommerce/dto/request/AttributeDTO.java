package com.example.ecommerce.dto.request;

import com.example.ecommerce.entity.AttributeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDTO {
    private Long attributeId;
    @NotBlank(message = "attributeName cannot be empty")
    private String attributeName;
    @NotBlank(message = "value cannot be empty")
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
