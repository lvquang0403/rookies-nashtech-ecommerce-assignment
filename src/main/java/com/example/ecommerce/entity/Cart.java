package com.example.ecommerce.entity;

import com.example.ecommerce.dto.response.CartDTO;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @Column
    private Date createdDate;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.REMOVE)
    private Set<CartItem> cartItems;
    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;


    public static CartDTO convertToDTO(Cart cart){
        return new CartDTO(cart.cartId, cart.createdDate);
    }
}
