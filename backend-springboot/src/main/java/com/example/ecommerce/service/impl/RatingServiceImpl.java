package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.dto.request.RatingPostDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Rating;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.RatingRepository;
import com.example.ecommerce.service.RatingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public RatingDTO createRating(RatingPostDTO ratingDTO) {
        Product foundProduct = productRepository.findById(ratingDTO.getProductId()).orElseThrow(
                () -> new NotFoundException(String.format("Product with id : %d is not found", ratingDTO.getProductId()))
        );
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long customerId = userDetails.getCustomerId();
        Customer foundCustomer = customerRepository.findById(customerId).orElse(null);
        Date currentDay = Date.valueOf(LocalDate.now());
        return RatingDTO.fromRating(ratingRepository.save(new Rating(
                ratingDTO.getComment(),
                foundProduct,
                currentDay,
                ratingDTO.getScore(),
                foundCustomer)));
    }

    @Override
    public List<RatingDTO> findAllByProductId(int pageNumber, int pageSize, Long productId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createDay").descending());
        List<Rating> ratings = ratingRepository.findAllByProductId(pageable, productId).getContent();
        return ratings.stream().map(RatingDTO::fromRating).collect(Collectors.toList());
    }

    @Override
    public void updateById(Long ratingId, RatingPostDTO ratingDTO) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException(String.format("Rating with id : %d is not found",ratingId))
        );

        if(!ratingDTO.getComment().isEmpty()){
            rating.setComment(ratingDTO.getComment());
        }
        if(ratingDTO.getScore() != null){
            rating.setScore(ratingDTO.getScore());
        }
        ratingRepository.save(rating);
    }

    @Override
    public void deleteById(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException(String.format("Rating with id : %d is not found",ratingId))
        );
        ratingRepository.deleteById(rating.getRatingId());
    }

//    @Override
//    public RatingDTO findByProductIdAndUserName(Long productId, String userName) {
//        return Rating.convertToDTO(ratingRepository.findByProductIdAndUserName(productId, userName)
//                .orElseThrow(
//                        () -> new NotFoundException(
//                                String.format("Rating with productId %s and userName %s is not found", productId, userName)
//                        )
//                )
//        );
//    }
}
