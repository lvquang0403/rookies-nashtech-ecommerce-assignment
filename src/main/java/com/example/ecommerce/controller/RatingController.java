package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.dto.request.RatingPostDTO;
import com.example.ecommerce.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    ResponseEntity<RatingPostDTO> createRating(@RequestBody @Valid RatingPostDTO ratingPostDTO){
        return ResponseEntity.ok(ratingService.createRating(ratingPostDTO));
    }

    @GetMapping("")
    @ResponseBody
    ResponseEntity<List<RatingDTO>> getRatingsByProductId(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "productId") Long productId
    ) {
        return ResponseEntity.ok(ratingService.findAllByProductId(pageNumber, pageSize, productId));
    }

    @GetMapping("avgRatingByProducId/{productId}")
    ResponseEntity<Object> avgRatingByProductId(@PathVariable Long productId){
        return ResponseEntity.ok(ratingService.getAvgRatingByProductId(productId));
    }

    @PutMapping("/{ratingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    ResponseEntity<RatingDTO> updateRating(
            @PathVariable Long ratingId,
            @RequestBody @Valid RatingDTO ratingDTO)
    {
        return ResponseEntity.ok(ratingService.updateById(ratingId, ratingDTO));
    }

}
