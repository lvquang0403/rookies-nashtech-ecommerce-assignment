package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;


import java.util.List;

public interface AttributeService {
    List<AttributeDTO> findByProductId(Long productId);
    List<AttributeDTO> findAll();
    AttributeDTO createAttribute(AttributePostDTO attributeDTO);
    void update(Long attributeId, String attributeName);
    void deleteById(Long id);
}
