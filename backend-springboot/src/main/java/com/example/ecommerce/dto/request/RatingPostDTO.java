package com.example.ecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingPostDTO {
    @NotNull
    private Long customerId;
    @NotBlank(message = "comment cannot be empty")
    private String comment;
    @Min(0)
    @Max(5)
    private Integer score;
    @NotNull(message = "productId cannot be empty")
    private Long productId;
}
