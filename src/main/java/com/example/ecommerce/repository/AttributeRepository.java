package com.example.ecommerce.repository;

import com.example.ecommerce.dto.AttributeDTO;
import com.example.ecommerce.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByAttributeNameIgnoreCase(String attributeName);
}
