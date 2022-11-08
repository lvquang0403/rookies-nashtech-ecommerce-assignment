package com.example.ecommerce.dto.response;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> roles;

    public static CustomerViewDTO fromCustomer(Customer customer){
        return new CustomerViewDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCreatedDate(),
                customer.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList())
        );
    }
}
