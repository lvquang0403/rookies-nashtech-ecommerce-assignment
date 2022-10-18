package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.service.AttributeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<Attribute> findByProductId(Long productId) {
        return attributeRepository.findByProductId(productId);
    }

}
