package com.phrms.HealthCareSystem.service.auth;

import com.phrms.HealthCareSystem.entity.OTP;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;
import com.phrms.HealthCareSystem.repository.OTPRepository;
import com.phrms.HealthCareSystem.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public LoginResponse verifyEmail(OTPResponse otp) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        OTP otpValue = otpRepository.findByEmail(otp.getEmail()).getLast();
        if (otpValue.getOtp().equals(otp.getOtp())){
            User userDetails = new User();
            userDetails.setAadharNumber(otp.getAadharNumber());
            loginResponse.setAccess_token(jwtUtil.generateToken(userDetails));
            loginResponse.setRefresh_token(jwtUtil.generateRefreshToken(userDetails));
            otpRepository.delete(otpValue);
            return loginResponse;
        }else {
            throw new Exception("Invalid OTP!");
        }
    }
}
