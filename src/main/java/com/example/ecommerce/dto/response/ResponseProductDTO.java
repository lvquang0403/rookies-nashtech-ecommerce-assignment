package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Product;
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

    public static ResponseProductDTO fromProduct(Product product){
        return new ResponseProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCreatedDate(),
                product.getUpdatedDate()
        );
    }
}
