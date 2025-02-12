package com.example.distance;

import com.example.distance.DTO.DistanceRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DistanceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetDistanceOk() throws Exception {
        DistanceRequestDTO request = new DistanceRequestDTO();
        request.setLatitudeFrom(48.8566);
        request.setLongitudeFrom(2.3522);
        request.setLatitudeTo(41.9028);
        request.setLongitudeTo(12.4964);

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void testGetDistanceInvalidRequest() throws Exception {
        DistanceRequestDTO invalidRequestDTO = new DistanceRequestDTO();
        invalidRequestDTO.setLatitudeFrom(41.9028);
        invalidRequestDTO.setLongitudeFrom(2.3522);

        mockMvc.perform(post("/api/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequestDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}