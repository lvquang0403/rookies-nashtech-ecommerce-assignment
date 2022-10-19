package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "product")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Image> images;
    @ManyToOne
    @JoinColumn(name = "products")
    private Category category;
    @ManyToMany
    private Set<Attribute> attributes;

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratingProducts;

    public Product(String productName, String description, BigDecimal price, Date createdDate, Date updatedDate, Category category, Set<Attribute> attributes, Set<Rating> ratings) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category = category;
        this.attributes = attributes;
        this.ratingProducts = ratings;
    }
}
