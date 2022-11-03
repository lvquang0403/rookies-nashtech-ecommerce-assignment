package com.example.ecommerce.service;
import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.dto.request.RatingPostDTO;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(RatingPostDTO ratingDTO);
    List<RatingDTO> findAllByProductId(int pageNumber, int pageSize, Long productId);
    void updateById(Long ratingId, RatingPostDTO ratingDTO);
    void deleteById(Long ratingId);
}
