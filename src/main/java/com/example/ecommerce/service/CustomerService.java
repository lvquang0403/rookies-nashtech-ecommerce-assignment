package com.example.ecommerce.service;


import com.example.ecommerce.dto.CustomerPostDTO;
import com.example.ecommerce.dto.CustomerViewDTO;

public interface CustomerService {
    CustomerViewDTO createCustomer(CustomerPostDTO customerPostDTO);
    void addRoleToCustomer(String userName, String roleName);
}
