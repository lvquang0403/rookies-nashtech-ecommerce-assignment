package com.example.ecommerce.controller;

import com.example.ecommerce.dto.response.ListProductViewDTO;
import com.example.ecommerce.dto.response.PageResponse;
import com.example.ecommerce.dto.response.ProductViewHomeDTO;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetProductsShouldReturnStatus200AndJsonArray() throws Exception {
        int pageSize = 1;
        int pageNumber = 1;
        PageResponse pageResponse = PageResponse.builder().pageNumber(pageNumber).pageSize(pageSize).totalPage(1).build();
        ProductViewHomeDTO productViewHomeDTO = ProductViewHomeDTO.builder().productName("productName").build();
        List<ProductViewHomeDTO> viewHomeDTOList = new ArrayList<>();
        viewHomeDTOList.add(productViewHomeDTO);
        ListProductViewDTO listProductViewDTO = ListProductViewDTO.builder().products(viewHomeDTOList).pageResponse(pageResponse).build();
        given(productService.findAll(pageNumber,pageSize)).willReturn(listProductViewDTO);
        mvc.perform(get("api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));
    }

    @Test
    void searchProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductsByCategoryId() {
    }
}