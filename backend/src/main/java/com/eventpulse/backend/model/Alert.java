package com.eventpulse.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity                          // tells JPA: this class maps to a DB table
@Table(name = "alerts")          // the table will be named "alerts"
public class Alert {

    @Id                          // this field is the primary key
    private String id;

    @Column(nullable = false)    // this column cannot be null in DB
    private String title;

    @Column(columnDefinition = "TEXT")   // TEXT type for long descriptions
    private String description;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // JPA requires a no-arg constructor
    protected Alert() {}

    // Our constructor for creating new alerts
    public Alert(String title, String description, String severity) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public String getId()               { return id; }
    public String getTitle()            { return title; }
    public String getDescription()      { return description; }
    public String getSeverity()         { return severity; }
    public String getStatus()           { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setter — only status changes after creation
    public void setStatus(String status) { this.status = status; }
}