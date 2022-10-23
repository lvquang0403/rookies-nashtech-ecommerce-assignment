package com.example.ecommerce.service;

import com.example.ecommerce.dto.AttributeDTO;

import java.util.List;

public interface AttributeService {

    List<AttributeDTO> findByProductId(Long productId);
    AttributeDTO createAttributeDTO(AttributeDTO attributeDTO);
    AttributeDTO findById(Long attributeId);
    List<AttributeDTO> findAll(int pageNumber, int pageSize);
    AttributeDTO updateById(Long attributeId, AttributeDTO attributeDTO);
    void deleteById(Long id);
}
