package com.eventpulse.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)  // don't include null fields in JSON
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;  // field-level errors for validation

    // Constructor for simple errors (404, 500)
    public ApiErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Constructor for validation errors (400) with field details
    public ApiErrorResponse(int status, String error, String message,
                            String path, Map<String, String> errors) {
        this(status, error, message, path);
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus()              { return status; }
    public String getError()            { return error; }
    public String getMessage()          { return message; }
    public String getPath()             { return path; }
    public Map<String, String> getErrors() { return errors; }
}