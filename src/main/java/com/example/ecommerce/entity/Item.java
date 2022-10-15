package com.example.ecommerce.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public class Item {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

}
