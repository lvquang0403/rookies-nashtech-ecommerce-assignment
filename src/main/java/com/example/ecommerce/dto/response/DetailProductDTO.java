package com.example.ecommerce.dto.response;

import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.dto.request.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DetailProductDTO {
    private Long productId;
    private List<ImageDTO> images;
    private List<AttributeDTO> attributes;

}
