package com.example.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
}
