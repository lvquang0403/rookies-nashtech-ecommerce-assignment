package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table
public class Category {
    @Id
    @GeneratedValue
    private Long categoryId;
    @Column
    private String categoryName;


    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products;
}
