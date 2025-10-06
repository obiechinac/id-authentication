package io.mosip.authentication.service.service;

import io.mosip.authentication.service.dto.AuditEventRequest;
import io.mosip.authentication.service.dto.AuditEventResponse;

public interface AuditEventService {

     AuditEventResponse logEvent(AuditEventRequest request);
}
