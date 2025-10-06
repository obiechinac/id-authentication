package io.mosip.authentication.service.controller;


import io.mosip.authentication.service.dto.AuditEventRequest;
import io.mosip.authentication.service.dto.AuditEventResponse;
import io.mosip.authentication.service.service.AuditEventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/audit")
public class AuditEventController {

    private final AuditEventService service;

    public AuditEventController(AuditEventService service) {
        this.service = service;
    }

    @Operation(summary = "Log audit event", description = "Stores an event with timestamp and returns eventId")
    @PostMapping("/log")
    public ResponseEntity<AuditEventResponse> logEvent(@Valid @RequestBody AuditEventRequest request) {
        log.info("AuditEventController logEvent: {} ",request);
        AuditEventResponse response = service.logEvent(request);
        return ResponseEntity.ok(response);
    }
}

