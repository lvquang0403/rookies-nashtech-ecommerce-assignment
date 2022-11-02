package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListProductViewDTO {
    private PageResponse pageResponse;
    private List<ProductViewHomeDTO> products;

}
