package com.example.ecommerce.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Item {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @OneToOne
    private Product product;

}
