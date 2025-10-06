package io.mosip.authentication.service.service;

import io.mosip.authentication.service.db.AuditEvent;
import io.mosip.authentication.service.dto.AuditEventRequest;
import io.mosip.authentication.service.dto.AuditEventResponse;
import io.mosip.authentication.service.repository.AuditEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class AuditEventServiceImpl implements AuditEventService{



    @Autowired
    private AuditEventRepository repository;



    @Override
    public AuditEventResponse logEvent(AuditEventRequest request) {
        log.info("AuditEventService logEvent: {} ",request);
        AuditEvent event = new AuditEvent();
        event.setEventType(request.getEventType());
        event.setDescription(request.getDescription());
        event.setUserId(request.getUserId());
        event.setTimestamp(Instant.now());

        AuditEvent saved = repository.save(event);
        return new AuditEventResponse(saved.getEventId(), saved.getTimestamp());
    }
}
