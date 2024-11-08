package com.phrms.HealthCareSystem.service.mail;

public interface MailService {
    void sendVerificationEmail(String to, String subject, String body);
}
