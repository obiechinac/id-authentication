package io.mosip.authentication.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.authentication.service.dto.AuditEventRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuditEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldLogEvent() throws Exception {
        AuditEventRequest req = new AuditEventRequest();
        req.setEventType("LOGIN");
        req.setDescription("User attempted login");
        req.setUserId("12345");

        String json = objectMapper.writeValueAsString(req);

        mockMvc.perform(post("/api/v1/audit/log")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void shouldFailValidationWhenMissingFields() throws Exception {
        AuditEventRequest req = new AuditEventRequest();
        req.setDescription("no required fields");

        String json = objectMapper.writeValueAsString(req);

        mockMvc.perform(post("/api/v1/audit/log")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}