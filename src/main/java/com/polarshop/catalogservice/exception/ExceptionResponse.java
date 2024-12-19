package com.polarshop.catalogservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;

    public ExceptionResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public static ExceptionResponse of(int status, String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.status = status;
        exceptionResponse.message = message;
        return exceptionResponse;
    }

    public static ExceptionResponse of(int status, String message, Map<String, String> errors) {
        ExceptionResponse exceptionResponse = of(status, message);
        exceptionResponse.errors = errors;
        return exceptionResponse;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
