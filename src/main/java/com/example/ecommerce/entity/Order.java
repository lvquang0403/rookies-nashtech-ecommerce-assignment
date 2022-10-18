package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column
    private Integer numberTypeItem;
    @Column
    private String orderName;
    @Column
    private String shipAddress;
    @Column
    private Integer orderState;
    @Column
    private String orderPhone;
    @Column
    private BigDecimal totalPrice;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "order")
    private Set<OrderItem> orderItems;

}
