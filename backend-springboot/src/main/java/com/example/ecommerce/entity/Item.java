package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String color;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


}
