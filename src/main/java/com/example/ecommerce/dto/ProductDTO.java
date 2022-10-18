package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private List<Long> attributeIds;
    private Long categoryId;
}
