package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.repository.AttributeProductRepository;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AttributeServiceImplTest {

    private AttributeRepository attributeRepository;
    private AttributeProductRepository attributeProductRepository;
    private ProductRepository productRepository;
    private AttributeService attributeService;

    @BeforeEach
    void beforeEach() {
        attributeService = new AttributeServiceImpl(attributeRepository, attributeProductRepository,productRepository );
        attributeRepository = Mockito.mock(AttributeRepository.class);
        attributeProductRepository = Mockito.mock(AttributeProductRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
    }


    @Test
    void testFindByProductIdWhenAttributeProductNotExistShouldThrowException() {
        Long productId = 1L;
        Long attributeId = 2L;
    }

    @Test
    void testFindAll() {
    }

    @Test
    void testCreateAttributeWhenAttributeNameExistShouldThrowException() {
        String existAttributeName = "exist-attribute-name";
        AttributePostDTO attribute = AttributePostDTO.builder().attributeName(existAttributeName).build();
        Mockito.when(attributeRepository.findByAttributeNameIgnoreCase(existAttributeName))
                .thenReturn(Optional.of(Mockito.mock(Attribute.class)));

        DuplicateException duplicateException = Assertions.assertThrows(DuplicateException.class,
                () -> attributeService.createAttribute(attribute));

        assertThat(duplicateException.getMessage()).isEqualTo("Attribute with name %s already exists");
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}