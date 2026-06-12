package com.eventpulse.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Alert {

    private String id;
    private String title;
    private String description;
    private String severity;       // LOW, MEDIUM, HIGH, CRITICAL
    private String status;         // OPEN, RESOLVED
    private LocalDateTime createdAt;

    // Constructor — called when we create a new Alert
    public Alert(String title, String description, String severity) {
        this.id = UUID.randomUUID().toString();  // auto-generate unique ID
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = "OPEN";                    // always starts OPEN
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public String getId()          { return id; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getSeverity()    { return severity; }
    public String getStatus()      { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setter — only status can change after creation
    public void setStatus(String status) { this.status = status; }
}