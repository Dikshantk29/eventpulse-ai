package com.eventpulse.backend.service;

import com.eventpulse.backend.dto.CreateAlertRequest;
import com.eventpulse.backend.model.Alert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service   // tells Spring: manage this class, make it injectable
public class AlertService {

    // In-memory storage — just a list for now
    // We will replace this with PostgreSQL in the next phase
    private final List<Alert> alerts = new ArrayList<>();

    public Alert createAlert(CreateAlertRequest request) {
        Alert alert = new Alert(
                request.getTitle(),
                request.getDescription(),
                request.getSeverity()
        );
        alerts.add(alert);
        return alert;
    }

    public List<Alert> getAllAlerts() {
        return new ArrayList<>(alerts);  // return a copy, not the real list
    }

    public Optional<Alert> getAlertById(String id) {
        return alerts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public boolean deleteAlert(String id) {
        return alerts.removeIf(a -> a.getId().equals(id));
    }
}