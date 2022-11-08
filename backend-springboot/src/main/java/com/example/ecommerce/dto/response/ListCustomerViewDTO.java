package com.example.ecommerce.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerViewDTO {
    private PageResponse pageResponse;
    private List<CustomerViewDTO> customers;
}
