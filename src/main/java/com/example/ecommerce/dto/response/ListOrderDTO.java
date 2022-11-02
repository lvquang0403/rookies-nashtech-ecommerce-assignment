package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListOrderDTO {
    private int pageNumber;
    private int pageSize;
    private List<OrderDTO> orders;
}
