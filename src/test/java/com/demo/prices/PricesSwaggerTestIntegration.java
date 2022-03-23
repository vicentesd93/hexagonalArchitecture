package com.demo.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesSwaggerTestIntegration {

    private static final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCallSwaggerInfoJsonTest() throws Exception {
        this.mockMvc.perform(
                        get(BASE_URL + "/" + "v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.openapi").exists());
    }

    @Test
    void whenCallSwaggerInfoHtmlTest() throws Exception {
        this.mockMvc.perform(
                        get(BASE_URL + "/" + "swagger-ui.html"))
                .andExpect(status().isFound());
    }
}