package com.example.ecommerce.entity;

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
    @GeneratedValue
    private Long attributeId;
    @Column
    private String attributeName;
    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "attributes")
    private Set<Product> products;
}
