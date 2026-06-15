package com.eventpulse.backend.repository;

import com.eventpulse.backend.model.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> {
    // JpaRepository<Alert, String> means:
    //   Alert  = the entity this repository manages
    //   String = the type of the primary key (our id is a String)
    //
    // Spring automatically generates:
    //   save(alert)          → INSERT or UPDATE
    //   findById(id)         → SELECT WHERE id = ?
    //   findAll()            → SELECT *
    //   deleteById(id)       → DELETE WHERE id = ?
    //   existsById(id)       → SELECT COUNT WHERE id = ?
    //   count()              → SELECT COUNT(*)

    // Dynamic filter query — severity and status are optional
    // If null is passed for either, that filter is ignored
    @Query("""
        SELECT a FROM Alert a
        WHERE (:severity IS NULL OR a.severity = :severity)
          AND (:status   IS NULL OR a.status   = :status)
        """)
    Page<Alert> findByFilters(
            @Param("severity") String severity,
            @Param("status")   String status,
            Pageable pageable
    );
}