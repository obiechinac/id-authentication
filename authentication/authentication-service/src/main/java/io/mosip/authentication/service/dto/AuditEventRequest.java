package io.mosip.authentication.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuditEventRequest {
    @NotBlank(message = "eventType is mandatory")
    private String eventType;

    private String description;

    @NotBlank(message = "userId is mandatory")
    private String userId;
}
