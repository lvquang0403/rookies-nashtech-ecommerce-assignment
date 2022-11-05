package com.example.ecommerce.controller;

import com.example.ecommerce.config.security.JwtUtils;
import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.response.JwtResponse;
import com.example.ecommerce.dto.request.LoginRequest;
import com.example.ecommerce.dto.request.SignupRequest;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails;
        try {userDetails = (UserDetailsImpl) authentication.getPrincipal();}
        catch (Exception e){
            throw new NotFoundException("asdas");
        }
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getCustomerId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(customerRepository.existsCustomerByUserName(signUpRequest.getUserName()))) {
            return ResponseEntity.badRequest().body("User Name already exists");
        }

        if (Boolean.TRUE.equals(customerRepository.existsCustomerByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        // Create new user's account
        Customer customer = Customer.builder()
                .phone(signUpRequest.getPhone())
                .email(signUpRequest.getEmail())
                .address(signUpRequest.getAddress())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .userName(signUpRequest.getUserName())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();

        roles.forEach(role -> {
            Role userRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        });

        customer.setRoles(roles);
        customerRepository.save(customer);

        return ResponseEntity.ok("User registered successfully!");
    }
}
