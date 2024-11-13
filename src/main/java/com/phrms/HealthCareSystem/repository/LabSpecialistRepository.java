package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.LabSpecialist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LabSpecialistRepository extends MongoRepository<LabSpecialist,String> {
    Optional<LabSpecialist> findByEmail(String email);
}
