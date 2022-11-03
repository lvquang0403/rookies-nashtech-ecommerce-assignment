package com.example.ecommerce.service;
import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.response.AttributeProductDTO;


public interface AttributeProductService {
    AttributeProductDTO findByProductIdAndAttributeId(Long productId, Long attributeId);
    AttributeProductDTO createAttributeProduct(AttributeProductDTO attributeProductDTO);
    AttributeProductDTO findById(Long attributeProductId);
    AttributeDTO updateById(Long attributeProductId, AttributeProductDTO attributeProductDTO);
    void deleteById(Long attributeProductId);
}
