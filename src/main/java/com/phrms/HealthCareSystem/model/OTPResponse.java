package com.phrms.HealthCareSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPResponse {
    private String email;
    private String otp;
    private String aadharNumber;
}
