package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AttributeDTO;
import com.example.ecommerce.dto.AttributeProductDTO;
import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.AttributeProduct;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.AttributeProductRepository;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AttributeProductService;
import org.springframework.stereotype.Service;

@Service
public class AttributeProductServiceImpl implements AttributeProductService {
    private final AttributeProductRepository attributeProductRepository;
    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    public AttributeProductServiceImpl(AttributeProductRepository attributeProductRepository, ProductRepository productRepository, AttributeRepository attributeRepository) {
        this.attributeProductRepository = attributeProductRepository;
        this.productRepository = productRepository;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public AttributeProductDTO findByProductIdAndAttributeId(Long productId, Long attributeId) {
        AttributeProduct foundAttributeProduct =  attributeProductRepository.findByProductIdAndAttributeId(productId, attributeId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("AttributeProduct with productId %s and attributeId %s is not found",productId, attributeId)
                    )
                );
        return AttributeProduct.convertToDTO(foundAttributeProduct);
    }
    @Override
    public AttributeProductDTO findById(Long attributeProductId) {
        return AttributeProduct.convertToDTO(attributeProductRepository.findById(attributeProductId).orElseThrow(
                () -> new NotFoundException(String.format("AttributeProduct with id %s is not found", attributeProductId))
            )
        );
    }

    @Override
    public AttributeProductDTO createAttributeProduct(AttributeProductDTO attributeProductDTO) {
        Product foundProduct = productRepository.findById(attributeProductDTO.getProductId())
                .orElseThrow(
                        () -> new NotFoundException(String.format("Product with id %s is not found ", attributeProductDTO.getProductId()))
                );
        Attribute foundAttribute = attributeRepository.findById(attributeProductDTO.getAttributeId())
                .orElseThrow(
                        () -> new NotFoundException(String.format("Attribute with id %s is not found ", attributeProductDTO.getAttributeId()))
                );
        return AttributeProduct.convertToDTO(attributeProductRepository.save(new AttributeProduct(
                attributeProductDTO.getValue(),
                foundProduct,
                foundAttribute
                )
            )
        );
    }


    @Override
    public AttributeDTO updateById(Long attributeProductId, AttributeProductDTO attributeProductDTO) {
        return null;
    }

    @Override
    public void deleteById(Long attributeProductId) {

    }
}
