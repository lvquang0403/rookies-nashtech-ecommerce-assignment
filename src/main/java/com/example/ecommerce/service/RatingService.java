package com.example.ecommerce.service;

import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.dto.request.RatingPostDTO;

import java.util.List;

public interface RatingService {
    RatingPostDTO createRating(RatingPostDTO ratingPostDTO);
    RatingDTO findById(Long ratingId);
    List<RatingDTO> findAllByProductId(int pageNumber, int pageSize, Long productId);
    Double getAvgRatingByProductId(Long productId);
    RatingDTO updateById(Long ratingId, RatingDTO ratingDTO);
    void deleteById(Long ratingId);
}
