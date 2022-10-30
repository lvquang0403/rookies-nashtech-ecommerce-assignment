package com.example.ecommerce.dto.response;

import com.example.ecommerce.dto.response.ItemViewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCartItemDTO {
    private int pageNumber;
    private int PageSize;
    private List<ItemViewDTO> items;
}
