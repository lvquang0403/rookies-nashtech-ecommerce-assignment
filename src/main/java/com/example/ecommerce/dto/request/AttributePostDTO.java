package com.example.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributePostDTO {
    @NotBlank(message = "attribute name cannot be empty")
    private String attributeName;
    private String description;

    public AttributePostDTO(String attributeName) {
        this.attributeName = attributeName;
    }
}
