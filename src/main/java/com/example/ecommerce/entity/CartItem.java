package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class CartItem extends Item {
    @Id
    @GeneratedValue
    private Long cartItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartItems")
    private Cart cart;
}
