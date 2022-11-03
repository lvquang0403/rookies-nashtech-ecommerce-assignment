package com.example.ecommerce.dto.response;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartId;
    private Date createDay;
}
