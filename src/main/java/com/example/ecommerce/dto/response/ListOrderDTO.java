package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ListOrderDTO {
    private PageResponse pageResponse;
    private List<OrderDTO> orders;
}
