package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.StillRelationException;
import com.example.ecommerce.repository.AttributeProductRepository;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


class AttributeServiceImplTest {

    private AttributeRepository attributeRepository;
    private AttributeProductRepository attributeProductRepository;
    private ProductRepository productRepository;
    private AttributeService attributeService;

    @BeforeEach
    void beforeEach() {
        attributeRepository = Mockito.mock(AttributeRepository.class);
        attributeProductRepository = Mockito.mock(AttributeProductRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        attributeService = new AttributeServiceImpl(attributeRepository, attributeProductRepository,productRepository );
    }


    @Test
    void testFindByProductIdWhenProductNotExistShouldThrowException() {
        Long notFoundProductId = 200L;
        Mockito.when(productRepository.findById())
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class,
                () -> attributeService.findByProductId(notFoundProductId));
        assertThat(notFoundException.getMessage()).isEqualTo("Product with id : 200 is not found");
    }
    @Test
    void testFindByProductIdWhenFoundShouldReturnListAttributeDTO(){
        Long productId = 200L;
        Product foundProduct = Product.builder().productId(200L)
        .build();
        List<AttributeProduct> expectedAttributeProduct = List.of(AttributeProduct.builder()
                .attribute(Attribute.builder().attributeId(1L).attributeName("attributeName").build())
                .build());
        List<AttributeDTO> expectedAttributes = List.of(AttributeDTO.builder().attributeName("attributeName").attributeId(1L).build());
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(foundProduct));
        Mockito.when(attributeProductRepository.findAllByProductProductId(productId)).thenReturn(expectedAttributeProduct);
        List<AttributeDTO> actual = attributeService.findByProductId(productId);

        assertThat(actual.get(0).getAttributeId()).isEqualTo(expectedAttributes.get(0).getAttributeId());
        assertThat(actual.get(0).getAttributeName()).isEqualTo(expectedAttributes.get(0).getAttributeName());
    }

    @Test
    void testFindAllAttribute() {
        List<AttributeDTO> expectedAttributeDTO = List.of(AttributeDTO.builder().attributeId(1L).attributeName("attribute-name").build());
        List<Attribute> attributes = List.of(Attribute.builder().attributeId(1L).attributeName("attribute-name").build());
        Mockito.when(attributeRepository.findAll())
                .thenReturn(attributes);

        List<AttributeDTO> actual = attributeService.findAll();

        assertThat(actual.get(0).getAttributeName()).isEqualTo(expectedAttributeDTO.get(0).getAttributeName());
        assertThat(actual.get(0).getAttributeId()).isEqualTo(expectedAttributeDTO.get(0).getAttributeId());


    }

    @Test
    void testCreateAttributeWhenAttributeNameExistShouldThrowException() {
        String existAttributeName = "exist-attribute-name";
        AttributePostDTO attribute = AttributePostDTO.builder().attributeName(existAttributeName).build();
        Mockito.when(attributeRepository.findByAttributeNameIgnoreCase(existAttributeName))
                .thenReturn(Optional.of(Mockito.mock(Attribute.class)));

        DuplicateException duplicateException = Assertions.assertThrows(DuplicateException.class,
                () -> attributeService.createAttribute(attribute));

        assertThat(duplicateException.getMessage()).isEqualTo("Attribute with name exist-attribute-name already exists");
    }

    @Test
    void testDeleteByIdWhenStillHaveReferentShouldThrowException() {
        Long foundAttributeId = 1L;
        Attribute foundAttribute = Attribute.builder().attributeId(foundAttributeId).build();
        Mockito.when(attributeRepository.findById(foundAttributeId))
                .thenReturn(Optional.of(foundAttribute));
        Mockito.when(attributeProductRepository.countAttributeProductByAttributeAttributeId(foundAttributeId))
                .thenReturn(2);

        StillRelationException relationException = Assertions.assertThrows(StillRelationException.class,
                () -> attributeService.deleteById(foundAttributeId));

        assertThat(relationException.getMessage()).isEqualTo(
                "Attribute still referent to another Product");
    }

    @Test
    void testUpdateByIdWhenFoundAttributeShouldUpdateNewValue() {
        Long foundAttributeId = 1L;
        Attribute foundAttribute = Attribute.builder().attributeName("old-name").build();
        Mockito.when(attributeRepository.findById(foundAttributeId)).thenReturn(Optional.of(foundAttribute));
        Mockito.when(attributeRepository.save(any(Attribute.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        attributeService.update(foundAttributeId, "new-name");
        ArgumentCaptor<Attribute> attributeArgumentCaptor = ArgumentCaptor.forClass(Attribute.class);
        Mockito.verify(attributeRepository).save(attributeArgumentCaptor.capture());
        Attribute actual = attributeArgumentCaptor.getValue();

        assertThat(actual.getAttributeName()).isEqualTo("new-name");
    }

    @Test
    void testUpdateByIdWhenAttributeNotFoundAttributeShouldThrowException() {
        Long notFoundAttributeId = 123L;
        String attributeName = "attributeName";
        Attribute expectedAttribute = Mockito.mock(Attribute.class);
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> attributeService.update(notFoundAttributeId, attributeName));
       assertThat(notFoundException.getMessage()).isEqualTo("Attribute with id 123 is not found");
    }

    @Test
    void testDeleteByIdWhenHaveNoReferentShouldDeleteSuccess() {
        Long foundAttributeId = 2L;
        Attribute foundAttribute = Attribute.builder().attributeId(foundAttributeId).build();
        Mockito.when(attributeRepository.findById(foundAttributeId))
                .thenReturn(Optional.of(foundAttribute));
        Mockito.when(attributeProductRepository.countAttributeProductByAttributeAttributeId(foundAttributeId))
                .thenReturn(0);

        attributeService.deleteById(foundAttributeId);

        Mockito.verify(attributeRepository).deleteById(2L);
    }
}