package com.phrms.HealthCareSystem.model;

import lombok.Data;

@Data
public class DoctorResponse {
    private String id;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private String specialization;
}
