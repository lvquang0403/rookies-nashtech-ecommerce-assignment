package com.example.ecommerce.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AttributePostDTO {
    @NotBlank(message = "attribute name cannot be empty")
    private String attributeName;
    private String description;

    public AttributePostDTO(String attributeName) {
        this.attributeName = attributeName;
    }
}
