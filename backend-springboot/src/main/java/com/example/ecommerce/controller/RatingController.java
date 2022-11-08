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
@CrossOrigin(origins="*")
@RequestMapping("api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<RatingDTO> createRating(@RequestBody @Valid RatingPostDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.createRating(ratingDTO));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<List<RatingDTO>> getRatingsByProductId(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "productId") Long productId
    ) {
        return ResponseEntity.ok(ratingService.findAllByProductId(pageNumber, pageSize, productId));
    }

    @PutMapping("{ratingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> updateRating(
            @PathVariable Long ratingId,
            @RequestBody @Valid RatingPostDTO ratingDTO) {
        ratingService.updateById(ratingId, ratingDTO);
        return ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("{ratingId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteById(ratingId);
        return ResponseEntity.ok("successfully");
    }

}
