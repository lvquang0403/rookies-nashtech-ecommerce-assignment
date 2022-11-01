package entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class OrderItem extends Item{
    @Id
    @Column
    @GeneratedValue
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderItem(Integer quantity, BigDecimal price, BigDecimal totalPrice, Product product, Order newOrder) {
        super(quantity, price, totalPrice, product);
        this.order = newOrder;
    }
}
