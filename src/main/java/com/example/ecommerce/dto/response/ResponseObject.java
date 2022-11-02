package com.example.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
