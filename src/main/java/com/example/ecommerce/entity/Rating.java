package com.example.ecommerce.entity;

import com.example.ecommerce.dto.RatingDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @Column
    private String comment;
    @Column
    private Integer start;
    @Column
    private Date createDay;


    @ManyToOne
    @JoinColumn(name = "ratingss")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "ratings")
    private Customer customer;

    public Rating(String comment, Product product, Date createDay,Integer start, Customer customer) {
        this.comment = comment;
        this.createDay = createDay;
        this.product = product;
        this.start = start;
        this.customer = customer;
    }

    public static RatingDTO convertToDTO(Rating rating){
        return new RatingDTO(
                rating.getCustomer().getUserName(),
                rating.getComment(),
                rating.getStart()
        );
    }
}
