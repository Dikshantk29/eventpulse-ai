package com.eventpulse.backend.repository;

import com.eventpulse.backend.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
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
}