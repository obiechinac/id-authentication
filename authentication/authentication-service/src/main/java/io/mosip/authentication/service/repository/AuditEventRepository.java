package io.mosip.authentication.service.repository;

import io.mosip.authentication.service.db.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditEventRepository extends JpaRepository<AuditEvent,Long> {
}
