package com.example.ecommerce.dto.request;
import com.example.ecommerce.dto.request.ItemPostDTO;
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
