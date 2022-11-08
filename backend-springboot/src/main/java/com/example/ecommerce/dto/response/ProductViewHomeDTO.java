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
    private String categoryName;
    private int numberRating;


    public ProductViewHomeDTO(Long productId, String productName, String description, BigDecimal price, List<ImageDTO> images, String productDetails, Long categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.images = images;
        this.productDetails = productDetails;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
