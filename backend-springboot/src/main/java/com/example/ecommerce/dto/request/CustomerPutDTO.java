package com.example.ecommerce.dto.request;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String password;
}
