package com.phrms.HealthCareSystem.service.auth;

import com.phrms.HealthCareSystem.dto.RefreshTokenRequest;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;

public interface AuthService {
    LoginResponse verifyEmail(OTPResponse otp) throws Exception;
    LoginResponse refreshToken(RefreshTokenRequest refreshToken) throws Exception;
}
