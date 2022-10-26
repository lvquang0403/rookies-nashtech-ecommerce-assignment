package com.example.ecommerce.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListItemPostDTO {
    private Long customerId;
    private List<ItemPostDTO> itemPostDTOS;
}
