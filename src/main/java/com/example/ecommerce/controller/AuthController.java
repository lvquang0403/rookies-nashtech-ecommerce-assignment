package com.example.ecommerce.controller;

import com.example.ecommerce.config.security.JwtUtils;
import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.response.JwtResponse;
import com.example.ecommerce.dto.request.LoginRequest;
import com.example.ecommerce.dto.request.SignupRequest;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getCustomerId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (customerRepository.existsCustomerByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.badRequest().body("userName already exists");
        }

        if (customerRepository.existsCustomerByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
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

        Set<String> strRoles = new HashSet<>(signUpRequest.getRoles());
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        customer.setRoles(roles);
        customerRepository.save(customer);

        return ResponseEntity.ok("User registered successfully!");
    }
}
