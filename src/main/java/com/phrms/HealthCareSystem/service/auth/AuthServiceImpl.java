package com.phrms.HealthCareSystem.service.auth;

import com.phrms.HealthCareSystem.dto.RefreshTokenRequest;
import com.phrms.HealthCareSystem.entity.OTP;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;
import com.phrms.HealthCareSystem.repository.OTPRepository;
import com.phrms.HealthCareSystem.repository.UserRepository;
import com.phrms.HealthCareSystem.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse verifyEmail(OTPResponse otp) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        List<OTP> otpList = otpRepository.findByEmail(otp.getEmail());
        OTP otpValue = otpList.get(otpList.size() - 1);
        if (otpValue.getOtp().equals(otp.getOtp())){
            User userDetails = new User();
            userDetails.setAadharNumber(otp.getAadharNumber());
            userDetails.setPatientName(userRepository.findByAadharNumber(otp.getAadharNumber()).get().getPatientName());
            loginResponse.setAccess_token(jwtUtil.generateToken(userDetails));
            loginResponse.setRefresh_token(jwtUtil.generateRefreshToken(userDetails));
            loginResponse.setName(userDetails.getPatientName());
            otpRepository.delete(otpValue);
            return loginResponse;
        }else {
            throw new Exception("Invalid OTP!");
        }
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshToken) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        User user = new User();
        user.setAadharNumber(refreshToken.getAadharNumber());
        if (jwtUtil.validateToken(refreshToken.getRefreshToken(),user)){
            loginResponse.setRefresh_token(jwtUtil.generateRefreshToken(user));
            loginResponse.setAccess_token(jwtUtil.generateToken(user));
            loginResponse.setName(userRepository.findByAadharNumber(refreshToken.getAadharNumber()).get().getPatientName());
            return loginResponse;
        }else {
            throw new Exception("Refresh Token Expired");
        }
    }
}
