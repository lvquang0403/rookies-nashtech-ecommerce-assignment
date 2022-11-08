package com.example.ecommerce.entity;
import com.example.ecommerce.dto.response.AttributeProductDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"productId", "attributeId"})
})
public class AttributeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeProductId;
    @Column
    private String value;

//    @Column
//    private Long attributeId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name ="attributeId")
    private Attribute attribute;
//    updatable = false, insertable = false
    public AttributeProduct(String value, Product product, Attribute attribute) {
        this.value = value;
        this.product = product;
        this.attribute = attribute;
    }

    public static AttributeProductDTO convertToDTO(AttributeProduct attributeProduct){
        return new AttributeProductDTO(
                attributeProduct.value,
                attributeProduct.getProduct().getProductId(),
                attributeProduct.getAttribute().getAttributeId()
        );
    }

}
