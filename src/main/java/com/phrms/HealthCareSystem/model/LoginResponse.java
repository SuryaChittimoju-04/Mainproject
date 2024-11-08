package com.phrms.HealthCareSystem.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String access_token;
    private String refresh_token;
    private String name;
}
