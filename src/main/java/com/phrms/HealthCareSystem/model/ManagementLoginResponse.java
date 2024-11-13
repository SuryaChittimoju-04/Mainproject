package com.phrms.HealthCareSystem.model;

import lombok.Data;

@Data
public class ManagementLoginResponse {
    private String name;
    private String id;
    private Boolean isHospital;
}
