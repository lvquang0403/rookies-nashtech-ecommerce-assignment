package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CustomerPostDTO;
import com.example.ecommerce.dto.CustomerViewDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    @Override
    public CustomerViewDTO createCustomer(CustomerPostDTO customerPostDTO) {
        if(customerRepository.findByUserName(customerPostDTO.getUserName()).isPresent()){
            throw new DuplicateException(String.format("User name %s already exist",customerPostDTO.getUserName()));
        }
        return CustomerViewDTO.fromCustomer(customerRepository.save(Customer.builder()
                        .firstName(customerPostDTO.getFirstName())
                        .lastName(customerPostDTO.getLastName())
                        .address(customerPostDTO.getAddress())
                        .phone(customerPostDTO.getPhone())
                        .email(customerPostDTO.getEmail())
                        .createdDate(customerPostDTO.getCreatedDate())
                .build())
        );
    }

    @Override
    public void addRoleToCustomer(String userName, String roleName) {

    }
}
