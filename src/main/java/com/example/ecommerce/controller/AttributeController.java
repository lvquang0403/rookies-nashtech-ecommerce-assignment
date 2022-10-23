package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AttributeDTO;
import com.example.ecommerce.response.ResponseObject;
import com.example.ecommerce.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attribute")
public class AttributeController {
    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping("createAttribute")
    @ResponseBody
    ResponseEntity<AttributeDTO> createAttribute(@RequestBody AttributeDTO attribute){
        return ResponseEntity.ok(attributeService.createAttribute(attribute));
    }

    @GetMapping("/getByProductId/{productId}")
    @ResponseBody
    ResponseEntity<List<AttributeDTO>> getAttributesByProductId(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(attributeService.findByProductId(productId));
    }

    @GetMapping("/getById/{attributeId}")
    @ResponseBody
    ResponseEntity<AttributeDTO> getAttributeById(@PathVariable Long attributeId){
        return ResponseEntity.ok(attributeService.findById(attributeId));
    }

    @PutMapping("updateAttribute/{attributeId}")
    @ResponseBody
    ResponseEntity<AttributeDTO> updateAttribute(
            @PathVariable Long attributeId,
            @RequestBody AttributeDTO attributeDTO)
    {
        return ResponseEntity.ok(attributeService.updateById(attributeId, attributeDTO));
    }

    @DeleteMapping("deleteAttribute/{attributeId}")
    ResponseEntity<ResponseObject> deleteAttribute(@PathVariable Long attributeId){
        attributeService.deleteById(attributeId);
        return ResponseEntity.ok(new ResponseObject("200", "successfully", null));
    }

}
