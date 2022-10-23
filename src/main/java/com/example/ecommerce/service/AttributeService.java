package com.example.ecommerce.service;

import com.example.ecommerce.dto.AttributeDTO;
import com.example.ecommerce.entity.Attribute;

import java.util.List;

public interface AttributeService {
    List<AttributeDTO> findByProductId(Long productId);
    AttributeDTO createAttribute(AttributeDTO attributeDTO);
    AttributeDTO findById(Long attributeId);
    AttributeDTO updateById(Long attributeId, AttributeDTO attributeDTO);
    void deleteById(Long id);
}
