package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Customer> customers;
}
