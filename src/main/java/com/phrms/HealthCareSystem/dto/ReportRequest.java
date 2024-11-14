package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class ReportRequest {
    private String patientId;
    private String name;
    private String image;
}
