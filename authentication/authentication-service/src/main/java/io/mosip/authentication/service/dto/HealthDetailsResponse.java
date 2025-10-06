package io.mosip.authentication.service.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class HealthDetailsResponse {
    private String status;
    private String timestamp;
    private String serviceName;
    private String version;
    private String environment;
    private String someConfig;

}
