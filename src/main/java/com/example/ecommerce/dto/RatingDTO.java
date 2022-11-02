package com.example.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private String userName;
    private String comment;
    private Integer score;
    private Long productId;

}
