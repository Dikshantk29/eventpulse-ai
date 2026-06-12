package com.eventpulse.backend.controller;

import com.eventpulse.backend.dto.CreateAlertRequest;
import com.eventpulse.backend.model.Alert;
import com.eventpulse.backend.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController              // handles HTTP requests, returns JSON automatically
@RequestMapping("/api/alerts")  // all endpoints start with /api/alerts
@CrossOrigin(origins = "http://localhost:5173/")  // allow React dev server
public class AlertController {

    private final AlertService alertService;

    // Spring injects AlertService automatically
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // POST /api/alerts — create a new alert
    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody CreateAlertRequest request) {
        Alert created = alertService.createAlert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/alerts — get all alerts
    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    // GET /api/alerts/{id} — get one alert
    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable String id) {
        return alertService.getAlertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/alerts/{id} — delete an alert
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable String id) {
        boolean deleted = alertService.deleteAlert(id);
        if (deleted) {
            return ResponseEntity.noContent().build();  // 204
        }
        return ResponseEntity.notFound().build();       // 404
    }
}