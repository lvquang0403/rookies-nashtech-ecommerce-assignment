package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Image;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
