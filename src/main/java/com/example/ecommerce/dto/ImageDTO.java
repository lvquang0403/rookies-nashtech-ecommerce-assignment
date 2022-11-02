package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ImageDTO {
    private String color;
    private String url;

    public static ImageDTO fromImage(Image image){
        return new ImageDTO(
                image.getColor(),
                image.getUrl()
        );
    }
}
