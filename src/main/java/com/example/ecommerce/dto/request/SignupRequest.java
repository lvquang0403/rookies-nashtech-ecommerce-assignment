package com.example.ecommerce.dto.request;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String address;
    @NotBlank(message = "Phone cannot be empty")
    private String phone;
    @NotBlank(message = "email cannot be empty")
    @Size(max = 50)
    @Email
    private String email;
    private Date createdDate;
    @NotBlank(message = "userName cannot be empty")
    @Size(min = 3, max = 20)
    private String userName;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 40)
    private String password;
}
