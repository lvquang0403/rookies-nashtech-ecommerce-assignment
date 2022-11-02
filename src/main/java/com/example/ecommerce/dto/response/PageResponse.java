package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
}
