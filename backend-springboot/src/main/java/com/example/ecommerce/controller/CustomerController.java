package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CustomerPutDTO;
import com.example.ecommerce.dto.response.CustomerViewDTO;
import com.example.ecommerce.dto.response.ListCustomerViewDTO;
import com.example.ecommerce.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CustomerViewDTO> findById(@PathVariable Long customerId){
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ListCustomerViewDTO> findAll(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "30", required = false) int pageSize
    ){
        return ResponseEntity.ok(customerService.findAll(pageSize, pageNumber));
    }

    @PutMapping("{customerId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerPutDTO customerPutDTO
    ) {
        customerService.updateCustomer(customerPutDTO, customerId);
        return ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteCustomer(
            @PathVariable Long customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Successfully");
    }

}

