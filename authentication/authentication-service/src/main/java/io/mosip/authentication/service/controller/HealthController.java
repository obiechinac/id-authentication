package io.mosip.authentication.service.controller;

import io.mosip.authentication.service.dto.HealthDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @Value("${mosip.id.auth.healthStatus:UP}")
    private String healthStatus;

    @Value("${mosip.service.name}")
    private String serviceName;

    @Value("${mosip.service.version}")
    private String version;

    @Value("${mosip.service.env}")
    private String environment;

    @Value("${mosip.id.auth.someConfig}")
    private String someConfig;

    @Operation(summary = "Get health details", description = "Returns health status and service metadata")
    @GetMapping("/details")
    public ResponseEntity<HealthDetailsResponse> getHealthDetails() {
        HealthDetailsResponse response = new HealthDetailsResponse(
                healthStatus,
                Instant.now().toString(),
                serviceName,
                version,
                environment,
                someConfig
        );

        if ("DOWN".equalsIgnoreCase(healthStatus)) {
            //  Service is Unavailable or down
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
