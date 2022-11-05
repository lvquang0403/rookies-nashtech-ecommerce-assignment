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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RatingServiceImplTest {

    private RatingServiceImpl ratingService;
    private RatingRepository ratingRepository;
    private final Long validProductId = 1L;
    private final Product validProduct = mock(Product.class);
    private final Customer validCustomer = mock(Customer.class);

    @BeforeAll
    void init() {
        Long validCustomerId = 123L;
        mockSecurityContext(validCustomerId);
        ratingRepository = mock(RatingRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(validProductId)).thenReturn(Optional.of(validProduct));
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById(validCustomerId)).thenReturn(Optional.of(validCustomer));
        ratingService = new RatingServiceImpl(ratingRepository, productRepository, customerRepository);
    }

    @Test
    void testCreateRatingWhenNotFoundProductShouldThrowNotFoundException() {
        Long invalidProductId = 123456L;
        RatingPostDTO ratingPostDTO = RatingPostDTO.builder().productId(invalidProductId).build();

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> ratingService.createRating(ratingPostDTO));

        assertThat(exception.getMessage()).isEqualTo("Product with id : 123456 is not found");
    }

    @Test
    void testCreateRatingWhenSuccessShouldReturnRatingDTO() {
        RatingPostDTO ratingPostDTO = RatingPostDTO.builder().score(132).comment("comment")
                .productId(validProductId).build();
        ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);
        when(ratingRepository.save(ratingCaptor.capture()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        RatingDTO actual = ratingService.createRating(ratingPostDTO);

        Rating savedRating = ratingCaptor.getValue();
        assertThat(actual.getComment()).isEqualTo("comment");
        assertThat(savedRating.getComment()).isEqualTo("comment");
        assertThat(actual.getScore()).isEqualTo(132);
        assertThat(savedRating.getScore()).isEqualTo(132);
        assertThat(savedRating.getProduct()).isEqualTo(validProduct);
        assertThat(savedRating.getCustomer()).isEqualTo(validCustomer);
    }

    private void mockSecurityContext(Long customerId) {
        UserDetailsImpl userDetails = new UserDetailsImpl(customerId, "", "", "", new ArrayList<>());
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
