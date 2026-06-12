package com.eventpulse.backend.dto;

public class CreateAlertRequest {

    private String title;
    private String description;
    private String severity;

    // Default constructor — required for JSON parsing
    public CreateAlertRequest() {}

    //getters and setters
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getSeverity()    { return severity; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setSeverity(String severity)       { this.severity = severity; }
}
