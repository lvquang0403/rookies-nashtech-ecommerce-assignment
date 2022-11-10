package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class CategoryControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    public void setup(){

    }

    @Test
    public void testFindAllWhenGetAllShouldReturnStatus200AndJsonArray() throws Exception {
        int pageSize = 1;
        int pageNumber = 1;
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().categoryId(1L).categoryName("attribute-name").build());
        given(categoryService.findAll(pageSize, pageNumber)).willReturn(categories);
        mvc.perform(get("api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));
    }

}