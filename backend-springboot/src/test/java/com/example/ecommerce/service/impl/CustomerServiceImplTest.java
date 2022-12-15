package com.example.ecommerce.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.example.ecommerce.dto.request.CustomerPutDTO;
import com.example.ecommerce.dto.response.CustomerViewDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Rating;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class CustomerServiceImplTest {

    private CustomerServiceImpl customerService;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private RatingRepository ratingRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        ratingRepository = Mockito.mock(RatingRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);

        customerService = new CustomerServiceImpl(customerRepository, passwordEncoder,
                orderRepository, ratingRepository);
    }

    @Test
    void testUpdateCustomerWhenValidShouldUpdateCustomerProperties() {
        Long foundCustomerId = 1L;
        Customer foundCustomer = new Customer();
        String rawPassword = "raw-password";
        Mockito.when(customerRepository.findById(foundCustomerId))
                .thenReturn(Optional.of(foundCustomer));
        Mockito.when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Mockito.when(passwordEncoder.encode(rawPassword)).thenReturn("encoded-password");
        CustomerPutDTO customerPutDTO = CustomerPutDTO.builder().firstName("firstname")
                .lastName("lastname").address("address").phone("phone").password("raw-password").build();

        customerService.updateCustomer(customerPutDTO, 1L);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer actual = customerArgumentCaptor.getValue();
        assertThat(actual.getFirstName()).isEqualTo("firstname");
        assertThat(actual.getLastName()).isEqualTo("lastname");
        assertThat(actual.getAddress()).isEqualTo("address");
        assertThat(actual.getPhone()).isEqualTo("phone");
        assertThat(actual.getPassword()).isEqualTo("encoded-password");
    }

    @Test
    void testDeleteCustomerWhenFoundCustomerShouldDeleteSuccess() {
        Long foundCustomerId = 1L;
        Customer foundCustomer = new Customer();
        List<Rating> ratingList = List.of(Mockito.mock(Rating.class));
        Mockito.when(ratingRepository.findAllByCustomerCustomerId(2l)).thenReturn(ratingList);
        Mockito.when(customerRepository.findById(foundCustomerId))
                .thenReturn(Optional.of(foundCustomer));

        customerService.deleteCustomer(1L);

        Mockito.verify(customerRepository).delete(foundCustomer);
    }

    @Test
    void testDeleteCustomerWhenNotFoundCustomerShouldThrowException() {
        Long customerId = 2L;
        List<Rating> ratingList = List.of(Mockito.mock(Rating.class));
        Mockito.when(ratingRepository.findAllByCustomerCustomerId(2l)).thenReturn(ratingList);
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class,
                () -> customerService.deleteCustomer(2L));
        assertThat(notFoundException.getMessage()).isEqualTo("Customer Not Found");
    }

    @Test
    void testFindByIdWhenFoundCustomerShouldReturnCustomerDto() {
        Long foundCustomerId = 1L;
        Customer foundCustomer = Customer.builder()
                .customerId(foundCustomerId).firstName("firstname").lastName("lastname")
                .address("address").phone("phone").email("email@gmail.com")
                .roles(new HashSet<>()).build();
        Mockito.when(customerRepository.findById(foundCustomerId))
                .thenReturn(Optional.of(foundCustomer));

        CustomerViewDTO actual = customerService.findById(foundCustomerId);

        assertThat(actual.getFirstName()).isEqualTo("firstname");
        assertThat(actual.getLastName()).isEqualTo("lastname");
        assertThat(actual.getAddress()).isEqualTo("address");
        assertThat(actual.getPhone()).isEqualTo("phone");
        assertThat(actual.getEmail()).isEqualTo("email@gmail.com");
        assertThat(actual.getRoles()).isEmpty();
    }
}
