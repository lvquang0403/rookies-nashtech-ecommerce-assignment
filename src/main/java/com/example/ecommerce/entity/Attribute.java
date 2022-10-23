package com.example.ecommerce.entity;

import com.example.ecommerce.dto.AttributeDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeId;
    @Column
    private String attributeName;
    @Column
    private String description;

    @OneToMany(mappedBy = "attribute")
    private Set<AttributeProduct> attributeProducts;

    public Attribute(String attributeName, String description) {
        this.attributeName = attributeName;
        this.description = description;
    }

    public static AttributeDTO convertToDTO(Attribute attribute){
        return new AttributeDTO(
                attribute.getAttributeId(),
                attribute.getAttributeName(),
                null,
                attribute.getDescription()
        );
    }
}
