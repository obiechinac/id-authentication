package io.mosip.authentication.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class AuditEventResponse {
    private Long eventId;
    private Instant timestamp;
}
