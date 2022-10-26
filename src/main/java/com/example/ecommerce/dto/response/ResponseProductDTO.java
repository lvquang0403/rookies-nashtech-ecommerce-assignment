package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class ResponseProductDTO {
    private Long productId;
    private String productName;
    private Date createDay;
    private Date updateDay;
}
