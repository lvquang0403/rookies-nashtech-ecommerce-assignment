package com.example.ecommerce.dto.response;

import com.example.ecommerce.dto.ImageDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductViewHomeDTO {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private List<ImageDTO> images;
    private String productDetails;
    private Long categoryId;


}
