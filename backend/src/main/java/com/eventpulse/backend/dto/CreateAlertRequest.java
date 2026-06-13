package com.eventpulse.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateAlertRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;   // optional — no @NotBlank

    @NotBlank(message = "Severity is required")
    @Pattern(
            regexp = "LOW|MEDIUM|HIGH|CRITICAL",
            message = "Severity must be LOW, MEDIUM, HIGH, or CRITICAL"
    )
    private String severity;

    // Default constructor — required for JSON deserialization
    public CreateAlertRequest() {}

    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getSeverity()    { return severity; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setSeverity(String severity)       { this.severity = severity; }
}