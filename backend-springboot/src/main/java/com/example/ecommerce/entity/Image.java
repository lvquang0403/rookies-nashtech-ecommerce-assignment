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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    @Column
    private String url;
    @Column
    private String color;

    @ManyToOne
    @JoinColumn(name = "producId")
    private Product product;
}
