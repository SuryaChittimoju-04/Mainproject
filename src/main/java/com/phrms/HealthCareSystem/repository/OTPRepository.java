package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface OTPRepository extends MongoRepository<OTP,String> {
    List<OTP> findByEmail(String email);
}
