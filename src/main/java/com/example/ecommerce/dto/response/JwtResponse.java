package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long customerId;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long customerId, String username, String email, List<String> roles) {
        this.token = token;
        this.customerId = customerId;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
