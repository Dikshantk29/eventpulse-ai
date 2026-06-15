package com.eventpulse.backend.service;

import com.eventpulse.backend.dto.CreateAlertRequest;
import com.eventpulse.backend.dto.PagedResponse;
import com.eventpulse.backend.exception.ResourceNotFoundException;
import com.eventpulse.backend.model.Alert;
import com.eventpulse.backend.repository.AlertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    private static final List<String> ALLOWED_SORT_FIELDS =
            List.of("createdAt", "severity", "status", "title");

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

    public PagedResponse<Alert> getAlerts(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String severity,
            String status) {

        int safeSize = Math.min(size, 50);

        String safeSortBy = validateSortField(sortBy);
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(safeSortBy).ascending()
                : Sort.by(safeSortBy).descending();

        Pageable pageable = PageRequest.of(page, safeSize, sort);

        String severityFilter = (severity != null && !severity.isBlank()) ? severity : null;
        String statusFilter   = (status   != null && !status.isBlank())   ? status   : null;

        Page<Alert> resultPage = alertRepository.findByFilters(
                severityFilter,
                statusFilter,
                pageable
        );

        return PagedResponse.from(resultPage);
    }

    public Alert getAlertById(String id) {
        return alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with id: " + id)
                );
    }

    public void deleteAlert(String id) {
        if (!alertRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alert not found with id: " + id);
        }
        alertRepository.deleteById(id);
    }

    private String validateSortField(String sortBy) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            return "createdAt";
        }
        return sortBy;
    }
}