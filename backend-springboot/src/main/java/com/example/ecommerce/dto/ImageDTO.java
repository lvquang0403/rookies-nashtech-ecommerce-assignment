package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Image;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    @NotBlank
    private String color;
    @NotBlank
    private String url;

    public static ImageDTO fromImage(Image image){
        return new ImageDTO(
                image.getColor(),
                image.getUrl()
        );
    }
}
