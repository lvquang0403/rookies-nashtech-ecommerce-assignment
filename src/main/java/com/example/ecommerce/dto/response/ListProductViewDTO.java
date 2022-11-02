package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListProductViewDTO {
    private int pageNumber;
    private int PageSize;
    private List<ProductViewHomeDTO> products;

}
