package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class DoctorLoginRequest {
    private String email;
    private String password;
    private String hospitalId;
}
