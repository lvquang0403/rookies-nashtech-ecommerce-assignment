package com.example.ecommerce.service;

import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.entity.Category;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(RatingDTO ratingDTO);
    RatingDTO findById(Long ratingId);
    List<RatingDTO> findAllByProductId(int pageNumber, int pageSize, Long productId);
    RatingDTO updateById(Long ratingId, RatingDTO ratingDTO);
    void deleteById(Long ratingId);
    RatingDTO findByProductIdAndUserName(Long productId, String userName);
}
