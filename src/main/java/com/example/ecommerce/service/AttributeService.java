package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;


import java.util.List;

public interface AttributeService {
    List<AttributeDTO> findByProductId(Long productId);

    AttributeDTO createAttribute(AttributePostDTO attributeDTO);

    //    AttributeDTO updateById(Long attributeId, AttributeDTO attributeDTO);
    void deleteById(Long id);
}
