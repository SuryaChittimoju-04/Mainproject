package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
    private String aadharNumber;
}
