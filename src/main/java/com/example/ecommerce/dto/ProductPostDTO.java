package com.example.ecommerce.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProductPostDTO {
    private String productName;
    private String description;
    private BigDecimal price;
    private List<Long> attributeIds;
    private Long categoryId;

}