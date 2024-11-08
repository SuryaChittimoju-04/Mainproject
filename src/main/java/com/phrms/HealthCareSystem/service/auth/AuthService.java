package com.phrms.HealthCareSystem.service.auth;

import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;

public interface AuthService {
    LoginResponse verifyEmail(OTPResponse otp) throws Exception;
}