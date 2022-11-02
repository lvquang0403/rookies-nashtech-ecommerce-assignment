package com.example.ecommerce.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPostDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private Date createdDate;
    private String userName;
    private String password;
}
