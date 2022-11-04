package com.example.ecommerce.dto.response;
import com.example.ecommerce.entity.Customer;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerViewDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private Date createdDate;

    public static CustomerViewDTO fromCustomer(Customer customer){
        return new CustomerViewDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCreatedDate()
        );
    }
}
