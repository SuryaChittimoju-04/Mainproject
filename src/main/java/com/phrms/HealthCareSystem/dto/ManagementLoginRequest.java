package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class ManagementLoginRequest {
    private String email;
    private String password;
    private Boolean isHospital;
}
