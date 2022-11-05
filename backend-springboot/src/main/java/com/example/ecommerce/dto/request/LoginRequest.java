package com.example.ecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "userName cannot be empty")
    @Size(min = 2, max = 30, message = "number character of userName must have from 2 to 30")
    private String username;
    @Size(min = 6, max = 40)
    @NotBlank(message = "password cannot be empty")
    private String password;

}
