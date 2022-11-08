package com.example.ecommerce.service;
import com.example.ecommerce.dto.request.CustomerPostDTO;
import com.example.ecommerce.dto.request.CustomerPutDTO;
import com.example.ecommerce.dto.response.CustomerViewDTO;
import com.example.ecommerce.dto.response.ListCustomerViewDTO;

public interface CustomerService {
    void createCustomer(CustomerPostDTO customerPostDTO);
    void addRoleToCustomer(String userName, String roleName);
    ListCustomerViewDTO findAll(int pageSize, int pageNumber);
    void updateCustomer(CustomerPutDTO customerPutDTO, Long customerId);
    void deleteCustomer(Long customerId);

    CustomerViewDTO findById(Long customerId);


}
