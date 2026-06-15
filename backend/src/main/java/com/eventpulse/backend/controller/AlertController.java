package com.eventpulse.backend.controller;

import com.eventpulse.backend.dto.CreateAlertRequest;
import com.eventpulse.backend.dto.PagedResponse;
import com.eventpulse.backend.model.Alert;
import com.eventpulse.backend.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "http://localhost:5173")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@Valid @RequestBody CreateAlertRequest request) {
        Alert created = alertService.createAlert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Alert>> getAlerts(
            // All parameters are optional — sensible defaults provided
            @RequestParam(defaultValue = "0")          int page,
            @RequestParam(defaultValue = "10")         int size,
            @RequestParam(defaultValue = "createdAt")  String sortBy,
            @RequestParam(defaultValue = "desc")       String sortDir,
            @RequestParam(required = false)            String severity,
            @RequestParam(required = false)            String status) {

        PagedResponse<Alert> response = alertService.getAlerts(
                page, size, sortBy, sortDir, severity, status
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable String id) {
        // No more Optional — service throws if not found, handler catches it
        Alert alert = alertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable String id) {
        alertService.deleteAlert(id);           // throws 404 if not found
        return ResponseEntity.noContent().build(); // 204 on success
    }
}