package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.response.ResponseObject;
import com.example.ecommerce.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("createRating")
    @ResponseBody
    ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO rating){
        return ResponseEntity.ok(ratingService.createRating(rating));
    }

    @GetMapping("ratings")
    @ResponseBody
    ResponseEntity<List<RatingDTO>> getRatingsByProductId(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "productId", required = true) Long productId
    ) {
        return ResponseEntity.ok(ratingService.findAllByProductId(pageNumber, pageSize, productId));
    }

    @GetMapping("/getById/{ratingId}")
    @ResponseBody
    ResponseEntity<RatingDTO> getRatingById(@PathVariable Long ratingId){
        return ResponseEntity.ok(ratingService.findById(ratingId));
    }

    @PutMapping("updateRating/{ratingId}")
    @ResponseBody
    ResponseEntity<RatingDTO> updateRating(
            @PathVariable Long ratingId,
            @RequestBody RatingDTO ratingDTO)
    {
        return ResponseEntity.ok(ratingService.updateById(ratingId, ratingDTO));
    }

    @DeleteMapping("deleteRating/{ratingId}")
    ResponseEntity<ResponseObject> deleteRating(@PathVariable Long ratingId){
        ratingService.deleteById(ratingId);
        return ResponseEntity.ok(new ResponseObject("200", "successfully", null));
    }

}
