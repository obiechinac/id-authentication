package io.mosip.authentication.service.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @Column(nullable = false)
    private String eventType;

    private String description;

    @Column(nullable = false)
    private String userId;

    private Instant timestamp;
}
