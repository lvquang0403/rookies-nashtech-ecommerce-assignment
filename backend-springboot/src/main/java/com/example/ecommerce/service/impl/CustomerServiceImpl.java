package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.CustomerPostDTO;
import com.example.ecommerce.dto.request.CustomerPutDTO;
import com.example.ecommerce.dto.response.CustomerViewDTO;
import com.example.ecommerce.dto.response.ListCustomerViewDTO;
import com.example.ecommerce.dto.response.PageResponse;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Rating;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.StillRelationException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.RatingRepository;
import com.example.ecommerce.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;
    private final OrderRepository orderRepository;
    private final RatingRepository ratingRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder encoder, OrderRepository orderRepository, RatingRepository ratingRepository) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.orderRepository = orderRepository;
        this.ratingRepository = ratingRepository;
    }


    @Override
    public void createCustomer(CustomerPostDTO customerPostDTO) {

    }

    @Override
    public void addRoleToCustomer(String userName, String roleName) {

    }

    @Override
    public ListCustomerViewDTO findAll(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<CustomerViewDTO> customerDTOs = customerPage.getContent().stream().map(CustomerViewDTO::fromCustomer).collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse(pageSize, pageNumber, customerPage.getTotalPages());
        return new ListCustomerViewDTO(pageResponse, customerDTOs);
    }

    @Override
    public void updateCustomer(CustomerPutDTO customerPutDTO, Long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow( () -> new NotFoundException("Customer Not Found"));

        if(!customerPutDTO.getFirstName().isEmpty()){
            foundCustomer.setFirstName(customerPutDTO.getFirstName());
        }
        if(!customerPutDTO.getLastName().isEmpty()){
            foundCustomer.setLastName(customerPutDTO.getLastName());
        }
        if(!customerPutDTO.getAddress().isEmpty()){
            foundCustomer.setAddress(customerPutDTO.getAddress());
        }
        if(!customerPutDTO.getPhone().isEmpty()){
            foundCustomer.setPhone(customerPutDTO.getPhone());
        }
        if(!customerPutDTO.getPassword().isEmpty()){
            foundCustomer.setPassword(encoder.encode(customerPutDTO.getPassword()));
        }
        customerRepository.save(foundCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        List<Rating> ratings = ratingRepository.findAllByCustomerCustomerId(customerId);
        if(!ratings.isEmpty()){
            ratingRepository.deleteAll(ratings);
        }
        List<Order> orders = orderRepository.findAllByCustomerCustomerId(customerId);
        if (!orders.isEmpty()){
            orderRepository.deleteAll(orders);
        }
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow( () -> new NotFoundException("Customer Not Found"));
        customerRepository.delete(foundCustomer);
    }

    @Override
    public CustomerViewDTO findById(Long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new NotFoundException("Customer not Found")
                );
        return CustomerViewDTO.fromCustomer(foundCustomer);
    }
}
