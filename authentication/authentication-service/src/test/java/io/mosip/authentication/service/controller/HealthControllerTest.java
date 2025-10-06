package io.mosip.authentication.service.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "mosip.id.auth.healthStatus=UP",
        "mosip.service.name=test-service",
        "mosip.service.version=0.0.1",
        "mosip.service.env=TEST",
        "mosip.id.auth.someConfig=cfg-value"})
public class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHealthDetails() throws Exception {
        mockMvc.perform(get("/api/v1/health/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.serviceName").value("test-service"))
                .andExpect(jsonPath("$.someConfig").value("cfg-value"));
    }
}
