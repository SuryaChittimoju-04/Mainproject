package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class UserDto {
    private String aadharNumber;
    private String patientPhNumber;
    private String patientEmail;
    private String patientName;
    private String patientAge;
    private String patientGender;
}
