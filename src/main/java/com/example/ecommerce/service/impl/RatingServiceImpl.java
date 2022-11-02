package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Rating;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.RatingRepository;
import com.example.ecommerce.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    public RatingDTO createRating(RatingDTO ratingDTO) {
        Product foundProduct = productRepository.findById(ratingDTO.getProductId()).orElseThrow(
                () -> new NotFoundException(String.format("Product with id : %d is not found",ratingDTO.getProductId()))
        );

        Customer customer = customerRepository.findByUserName(ratingDTO.getUserName()).orElseThrow(
                () -> new NotFoundException((String.format("Customer with userName : %s is not found",ratingDTO.getUserName())))
        );
        Date currentDay = Date.valueOf(LocalDate.now());
        ratingRepository.save(new Rating(
                ratingDTO.getComment(),
                foundProduct,
                currentDay,
                ratingDTO.getStart(),
                customer));
        return ratingDTO;
    }

    @Override
    public RatingDTO findById(Long ratingId) {
        Rating foundRating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException(String.format("Rating with id : %d is not found",ratingId))
        );
//        return new RatingDTO(
//                foundRating.getCustomer().getUserName(),
//                foundRating.getComment(),
//                foundRating.getStart(),
//                foundRating.getProduct().getProductId()
//        );
        return Rating.convertToDTO(foundRating);
    }

    @Override
    public List<RatingDTO> findAllByProductId(int pageNumber, int pageSize, Long productId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createDay").descending());
        List<Rating> ratings = ratingRepository.findAllByProductId(pageable, productId).getContent();
        return ratings.stream().map(rating -> new RatingDTO(
                rating.getCustomer().getUserName(),
                rating.getComment(),
                rating.getStart(),
                rating.getProduct().getProductId()
                )
        ).toList();
    }

    @Override
    public RatingDTO updateById(Long ratingId, RatingDTO ratingDTO) {
        if(ratingDTO.getUserName() != null){
            throw new BadRequestException("cannot change userName field of Rating");
        }
        if(ratingDTO.getProductId() != null){
            throw new BadRequestException("cannot change productId field of Rating");
        }
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException(String.format("Rating with id : %d is not found",ratingId))
        );

        if(!ratingDTO.getComment().isEmpty()){
            rating.setComment(ratingDTO.getComment());
        }
        if(ratingDTO.getStart() != null){
            rating.setStart(ratingDTO.getStart());
        }
        ratingRepository.save(rating);
        return new RatingDTO(
                rating.getCustomer().getUserName(),
                rating.getComment(),
                rating.getStart(),
                rating.getProduct().getProductId()
        );
    }

    @Override
    public void deleteById(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException(String.format("Rating with id : %d is not found",ratingId))
        );
        ratingRepository.deleteById(rating.getRatingId());
    }

    @Override
    public RatingDTO findByProductIdAndUserName(Long productId, String userName) {
        return Rating.convertToDTO(ratingRepository.findByProductIdAndUserName(productId, userName)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Rating with productId %s and userName %s is not found", productId, userName)
                        )
                )
        );
    }
}
