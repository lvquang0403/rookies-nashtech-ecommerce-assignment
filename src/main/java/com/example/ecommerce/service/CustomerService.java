package com.example.ecommerce.service;
import com.example.ecommerce.dto.request.CustomerPostDTO;

public interface CustomerService {
    void createCustomer(CustomerPostDTO customerPostDTO);
    void addRoleToCustomer(String userName, String roleName);
}
