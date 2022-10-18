//package com.example.ecommerce.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table
//public class Account {
//    @Id
//    @GeneratedValue
//    private Long accountId;
//    @Column
//    private String userName;
//    @Column
//    private String password;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Role> roles;
//
//}
