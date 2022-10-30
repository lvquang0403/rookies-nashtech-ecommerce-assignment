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
    private Integer start;
    private Long productId;

    public RatingDTO(String userName, String comment, Integer start) {
        this.userName = userName;
        this.comment = comment;
        this.start = start;
    }
}
