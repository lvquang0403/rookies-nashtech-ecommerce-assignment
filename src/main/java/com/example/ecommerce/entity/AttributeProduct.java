package com.example.ecommerce.entity;
import com.example.ecommerce.dto.AttributeProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"productId", "attributeId"})
})
public class AttributeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeProductId;
    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name ="attributeId")
    private Attribute attribute;

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
