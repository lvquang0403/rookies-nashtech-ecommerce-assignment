package com.example.ecommerce.dto.request;

import com.example.ecommerce.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Date createdDate;
    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    private List<String> roles;
}
