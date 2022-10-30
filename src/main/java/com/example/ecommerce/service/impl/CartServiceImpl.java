package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.response.CartDTO;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CartServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CartDTO createCart() {
        Date currentDay = Date.valueOf(LocalDate.now());
        return Cart.convertToDTO(cartRepository.save(Cart.builder().createdDate(currentDay).build()));
    }

    @Override
    public CartDTO findByCustomerId(Long customerId) {
        if(customerRepository.findById(customerId).isEmpty()){
            throw new NotFoundException(String.format("Customer with id %s is not found", customerId));
        }
        return Cart.convertToDTO(cartRepository.findByCustomerCustomerId(customerId).orElseThrow(
                () -> new NotFoundException(String.format("Not found Cart of Customer with id %s", customerId))
                )
        );
    }

}
