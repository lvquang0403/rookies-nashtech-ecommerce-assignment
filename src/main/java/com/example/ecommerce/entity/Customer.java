package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
