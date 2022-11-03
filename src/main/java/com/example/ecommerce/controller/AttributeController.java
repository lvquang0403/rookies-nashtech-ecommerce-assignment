package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attribute")
public class AttributeController {
    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AttributeDTO> createAttribute(@RequestBody AttributePostDTO attributeDTO){
        return ResponseEntity.ok(attributeService.createAttribute(attributeDTO));
    }

    @GetMapping("/getByProductId/{productId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<List<AttributeDTO>> getAttributesByProductId(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(attributeService.findByProductId(productId));
    }

    @DeleteMapping("{attributeId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteAttribute(@PathVariable Long attributeId){
        attributeService.deleteById(attributeId);
        return ResponseEntity.ok("successfully");
    }

}
