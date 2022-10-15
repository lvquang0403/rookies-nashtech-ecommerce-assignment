package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;
    @Column
    private Date createdDate;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "cart")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CartItem> cartItems;
}
