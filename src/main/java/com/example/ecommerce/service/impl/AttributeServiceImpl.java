package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.StillRelationException;
import com.example.ecommerce.repository.AttributeProductRepository;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeProductRepository attributeProductRepository;
    private final ProductRepository productRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository, AttributeProductRepository attributeProductRepository, ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.attributeProductRepository = attributeProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<AttributeDTO> findByProductId(Long productId) {
        if(productRepository.findById(productId).isEmpty()){
            throw new NotFoundException(String.format("Product with id : %d is not found", productId));
        }

        List<AttributeDTO> attributeDTOS = attributeProductRepository.findAllByProductProductId(productId)
                .stream()
                .map(AttributeDTO::fromAttributeProduct).toList();

        if(attributeDTOS.isEmpty()){
            throw new BadRequestException(String.format("Product with id %d don't contain any attribute",productId));
        }
        return attributeDTOS;
    }

    @Override
    public AttributeDTO createAttribute(AttributePostDTO attributeDTO) {
        if(attributeRepository.findByAttributeNameIgnoreCase(attributeDTO.getAttributeName()).isPresent()){
            throw new DuplicateException(String.format("Attribute with name %s already exists",attributeDTO.getAttributeName()));
        }

        Attribute attribute = attributeRepository.save(new Attribute(
                attributeDTO.getAttributeName(),
                attributeDTO.getDescription()
        ));

        return new AttributeDTO(
                attribute.getAttributeId(),
                attribute.getAttributeName()
        );
    }

//    @Override
//    public AttributeDTO findById(Long attributeId) {
//        Attribute foundAttribute = attributeRepository.findById(attributeId).orElseThrow(
//                () -> new NotFoundException(String.format("Attribute with id : %d is not found",attributeId))
//        );
//        return AttributeDTO.fromAttribute(foundAttribute)
//    }

//    @Override
//    public AttributeDTO updateById(Long attributeId, AttributeDTO attributeDTO) {
//        Attribute foundAttribute = attributeRepository.findById(attributeId).orElseThrow(
//                () -> new NotFoundException(String.format("Attribute with id : %d is not found",attributeId))
//        );
//        if(!attributeDTO.getAttributeName().isEmpty()){
//            foundAttribute.setAttributeName(attributeDTO.getAttributeName());
//        }
//        if(!attributeDTO.getDescription().isEmpty()){
//            foundAttribute.setDescription((attributeDTO.getDescription()));
//        }
//
//        return Attribute.convertToDTO(attributeRepository.save(foundAttribute));
//    }

    @Override
    public void deleteById(Long attributeId) {
        Attribute foundAttribute = attributeRepository.findById(attributeId).orElseThrow(
                () -> new NotFoundException(String.format("Attribute with id : %d is not found",attributeId))
        );
        if(attributeProductRepository.countAttributeProductByAttributeAttributeId(attributeId) > 0){
            throw new StillRelationException(String.format("Attribute with id %s still referent to AttributeProduct table",attributeId));
        }
        attributeRepository.deleteById(foundAttribute.getAttributeId());
    }

}
