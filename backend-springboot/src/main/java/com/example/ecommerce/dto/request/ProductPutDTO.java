package com.example.ecommerce.dto.request;

import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.entity.Image;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPutDTO {
    @NotBlank(message = "ProductName cannot be empty")
    private String productName;
    private String description;
    @Min(value = 0, message = "min price of product is 0")
    @NotNull(message = "price cannot be empty")
    private BigDecimal price;
    @Valid
    private List<AttributeDTO> attributes;
    @Valid
    private List<ImageDTO> images;
    @NotNull(message = "categoryId cannot be empty")
    private Long categoryId;

}
