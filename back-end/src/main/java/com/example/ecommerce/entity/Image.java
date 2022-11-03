package com.example.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Image {
    @Id
    @GeneratedValue
    private Long imageId;
    @Column
    private String url;
    @Column
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producId")
    private Product product;
}
