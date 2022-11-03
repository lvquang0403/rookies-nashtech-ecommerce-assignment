package com.example.ecommerce.dto;


import com.example.ecommerce.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private String fullName;
    private String comment;
    private Integer score;

    public static RatingDTO fromRating(Rating rating){
        return new RatingDTO(
                rating.getCustomer().getFirstName() + rating.getCustomer().getLastName(),
                rating.getComment(),
                rating.getScore()
        );
    }

}
