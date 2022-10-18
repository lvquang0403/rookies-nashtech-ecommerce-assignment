package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListProductDTO {
    private int pageNumber;
    private int PageSize;
    private List<ProductDTO> productDTOs;

}
