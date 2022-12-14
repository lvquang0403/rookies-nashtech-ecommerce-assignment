package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
    private Date createDay;
    @Column
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

}
