package com.example.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingPostDTO {
    @NotBlank(message = "comment cannot be empty")
    private String comment;
    @Min(0)
    @Max(5)
    private Integer score;
    @NotNull(message = "productId cannot be empty")
    private Long productId;
}
