package com.phrms.HealthCareSystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String message;
    private Object data;
}

