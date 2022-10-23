package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class OrderItem extends Item{
    @Id
    @Column
    @GeneratedValue
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderItems")
    private Order order;
}
