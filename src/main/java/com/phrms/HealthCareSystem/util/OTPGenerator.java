package com.phrms.HealthCareSystem.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OTPGenerator {
    private final int OTP_LENGTH = 6;

    public String generateOtp() {
        return String.valueOf(new Random().nextInt(999999)).substring(0, OTP_LENGTH);
    }
}
