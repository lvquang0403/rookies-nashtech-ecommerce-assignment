package com.example.ecommerce.dto.request;

import com.example.ecommerce.validation.constant.Regex;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPostDTO {
    @NotBlank(message = "orderName cannot be empty")
    private String orderName;
    @NotBlank(message = "customerName cannot be empty")
    private String customerName;
    @NotBlank(message = "orderName cannot be empty")
    @Pattern(regexp = Regex.PHONE_PATTERN)
    private String orderPhone;
    @NotBlank(message = "address is not empty")
        private String address;
    @NotNull(message = "totalPrice is not empty")
    private BigDecimal totalPrice;
}
