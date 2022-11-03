package com.example.ecommerce.dto.request;
import com.example.ecommerce.dto.ImageDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostDTO {
    @NotBlank(message = "ProductName cannot be empty")
    private String productName;
    private String description;
    @NotNull(message = "price cannot be empty")
    @Min(value = 0, message = "min price of product is 0")
    private BigDecimal price;
    private List<ImageDTO> images;
    @NotEmpty
    private String productDetails;
    @Valid
    private List<AttributeDTO> attributes;
    @NotNull(message = "categoryId cannot be null")
    private Long categoryId;

}
