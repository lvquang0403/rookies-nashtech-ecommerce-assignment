package com.example.ecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
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
