package com.example.ecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemPutDTO {
    private Long cartItemId;
    @NotNull(message = "Quantity must > 0")
    @Min(value = 1, message = "Quantity must > 0")
    private Integer quantity;
}
