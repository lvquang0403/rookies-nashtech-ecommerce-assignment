package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @Column
    private String comment;
    @Column
    private Integer score;
    @Column
    private Date createDay;


    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Rating(String comment, Product product, Date createDay,Integer score, Customer customer) {
        this.comment = comment;
        this.createDay = createDay;
        this.product = product;
        this.score = score;
        this.customer = customer;
    }
}
