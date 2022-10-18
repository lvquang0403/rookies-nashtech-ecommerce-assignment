package com.example.ecommerce.service;

import com.example.ecommerce.entity.Attribute;

import java.util.List;

public interface AttributeService {
    List<Attribute> findByProductId(Long productId);
}
