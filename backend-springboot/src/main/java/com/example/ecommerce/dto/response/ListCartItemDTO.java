package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCartItemDTO {
    private int pageNumber;
    private int PageSize;
    private List<ItemViewDTO> items;
}
