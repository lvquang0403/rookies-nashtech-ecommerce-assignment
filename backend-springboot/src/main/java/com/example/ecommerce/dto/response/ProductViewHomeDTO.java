package com.example.ecommerce.dto.response;

import com.example.ecommerce.dto.ImageDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewHomeDTO {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private List<ImageDTO> images;
    private String productDetails;
    private Long categoryId;
    private int numberRating;


}