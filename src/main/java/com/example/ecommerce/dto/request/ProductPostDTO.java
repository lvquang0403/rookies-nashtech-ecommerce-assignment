package com.example.ecommerce.dto;
import com.example.ecommerce.dto.AttributeDTO;
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
    private List<AttributeDTO> attributeDTOS;
    private Long categoryId;

}
