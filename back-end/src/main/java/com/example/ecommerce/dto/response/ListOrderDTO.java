package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderDTO {
    private int pageNumber;
    private int pageSize;
    private List<OrderDTO> orders;
}
