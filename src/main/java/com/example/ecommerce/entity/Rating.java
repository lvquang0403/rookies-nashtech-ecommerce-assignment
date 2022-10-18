package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Rating {
    @Id
    @GeneratedValue
    private Long productId;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "ratings")
    private Product product;
}
