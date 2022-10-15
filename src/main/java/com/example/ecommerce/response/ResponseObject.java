package com.example.ecommerce.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
