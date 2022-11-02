package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class CartItem extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private Cart cart;


    public CartItem(Integer quantity, BigDecimal price, BigDecimal totalPrice,String color, Product product, Cart cart) {
        super(quantity, price, totalPrice,color, product);
        this.cart = cart;
    }
}
