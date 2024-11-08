package com.phrms.HealthCareSystem.service.token;

import com.phrms.HealthCareSystem.entity.OTP;
import com.phrms.HealthCareSystem.entity.RefreshToken;
import com.phrms.HealthCareSystem.repository.OTPRepository;
import com.phrms.HealthCareSystem.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenCleanupService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Scheduled(fixedRate = 60000 * 10)
    private void removeExpiredTokens(){
        List<RefreshToken> refreshTokens = refreshTokenRepository.findAll();
        List<OTP> otps = otpRepository.findAll();

        List<RefreshToken> refreshTokensToBeDeleted = refreshTokens.stream()
                .filter(refreshToken -> refreshToken.getExpiryDate().before(new Date()) || refreshToken.isRevoked())
                .toList();

        List<OTP> otpsToBeDeleted = otps.stream()
                .filter(otp -> otp.getExpiresAt().before(new Date()))
                .toList();

        if (!refreshTokensToBeDeleted.isEmpty()){
            refreshTokenRepository.deleteAll(refreshTokensToBeDeleted);
        }
        if (!otpsToBeDeleted.isEmpty()){
            otpRepository.deleteAll(otpsToBeDeleted);
        }
    }
}
