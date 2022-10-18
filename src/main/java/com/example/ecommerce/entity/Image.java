//package com.example.ecommerce.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table
//public class Image {
//    @Id
//    @GeneratedValue
//    private Long imageId;
//    @Column
//    private String url;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "images")
//    private Product product;
//}
