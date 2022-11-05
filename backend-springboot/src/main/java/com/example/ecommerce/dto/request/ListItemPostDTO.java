package com.example.ecommerce.dto.request;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListItemPostDTO {
    private Long customerId;
    private List<ItemPostDTO> itemPostDTOS;
}
