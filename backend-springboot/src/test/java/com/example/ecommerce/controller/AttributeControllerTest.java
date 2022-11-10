package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.service.AttributeService;
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
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AttributeControllerTest {
    @MockBean
    private AttributeService attributeService;

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    public void setup(){

    }


    @Test
    public void testFindAllWhenGetAllShouldReturnStatus200AndJsonArray() throws Exception {
        List <AttributeDTO> attributeDTOS = new ArrayList<>();
        attributeDTOS.add(AttributeDTO.builder().attributeId(1L).attributeName("attribute-name").build());
        given(attributeService.findAll()).willReturn(attributeDTOS);
        mvc.perform(get("api/v1/attributes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));
    }

    @Test void testGetAttributesByProductIdShouldReturnStatus200AndJsonArray() throws Exception {
        Long productId = 1L;
        List<AttributeDTO> attributeDTOS = new ArrayList<>();
        attributeDTOS.add(AttributeDTO.builder().attributeId(1L).attributeName("attribute-name").build());
        given(attributeService.findByProductId(productId)).willReturn(attributeDTOS);
        mvc.perform(get("api/v1/attributes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"));
    }

}