package com.example.ecommerce.service;

import com.example.ecommerce.dto.AttributeDTO;
import com.example.ecommerce.dto.AttributeProductDTO;
import com.example.ecommerce.entity.AttributeProduct;

import java.util.List;

public interface AttributeProductService {
    AttributeProductDTO findByProductIdAndAttributeId(Long productId, Long attributeId);
    AttributeProductDTO createAttributeProduct(AttributeProductDTO attributeProductDTO);
    AttributeProductDTO findById(Long attributeProductId);
    AttributeDTO updateById(Long attributeProductId, AttributeProductDTO attributeProductDTO);
    void deleteById(Long attributeProductId);
}
