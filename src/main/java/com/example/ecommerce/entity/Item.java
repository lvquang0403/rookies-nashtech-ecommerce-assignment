package entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;


}
