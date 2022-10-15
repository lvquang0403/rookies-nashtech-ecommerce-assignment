package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    @Column
    private String productName;
    @Column
    private Integer categoryId;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private String image;
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Attribute> attributes;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Rating> ratings;
}
