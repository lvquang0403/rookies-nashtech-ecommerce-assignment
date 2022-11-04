package com.example.ecommerce.dto.response;

import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.dto.request.AttributeDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductDTO {
    private Long productId;
    private String productName;
    private List<ImageDTO> images;
    private List<AttributeDTO> attributes;
    private BigDecimal price;
    private int numberRating;

}
