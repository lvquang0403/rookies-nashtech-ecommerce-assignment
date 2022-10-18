package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long customerId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private Date createdDate;
    @Column
    private String userName;
    @Column
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
}
