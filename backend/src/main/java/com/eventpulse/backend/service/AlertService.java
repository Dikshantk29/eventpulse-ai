package com.eventpulse.backend.service;

import com.eventpulse.backend.dto.CreateAlertRequest;
import com.eventpulse.backend.exception.ResourceNotFoundException;
import com.eventpulse.backend.model.Alert;
import com.eventpulse.backend.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert createAlert(CreateAlertRequest request) {
        Alert alert = new Alert(
                request.getTitle(),
                request.getDescription(),
                request.getSeverity()
        );
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(String id) {
        // Now throws instead of returning Optional
        return alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with id: " + id)
                );
    }

    public void deleteAlert(String id) {
        // Verify it exists first — throw 404 if not
        if (!alertRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alert not found with id: " + id);
        }
        alertRepository.deleteById(id);
    }
}