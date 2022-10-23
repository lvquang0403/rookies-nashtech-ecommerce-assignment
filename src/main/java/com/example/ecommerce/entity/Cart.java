package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @Column
    private Date createdDate;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
    private Set<CartItem> cartItems;
}
