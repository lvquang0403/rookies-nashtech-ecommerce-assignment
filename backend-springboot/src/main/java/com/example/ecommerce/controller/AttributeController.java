package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/attributes")
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

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<List<AttributeDTO>> getAttributes() {
        return ResponseEntity.ok(attributeService.findAll());
    }


    @PutMapping("{attributeId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> update(
            @PathVariable Long attributeId,
            @RequestParam String attributeName) {
        attributeService.update(attributeId, attributeName);
        return ResponseEntity.ok("Successfully");
    }
    @DeleteMapping("{attributeId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteAttribute(@PathVariable Long attributeId){
        attributeService.deleteById(attributeId);
        return ResponseEntity.ok("successfully");
    }

}
