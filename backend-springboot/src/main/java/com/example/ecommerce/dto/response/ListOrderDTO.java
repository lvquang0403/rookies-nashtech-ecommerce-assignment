package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderDTO {
    PageResponse pageResponse;
    private List<OrderDTO> orders;
}
