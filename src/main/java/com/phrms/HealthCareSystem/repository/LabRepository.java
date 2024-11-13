package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.Laboratory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LabRepository extends MongoRepository<Laboratory,String> {
    Optional<Laboratory> findByEmail(String email);
}
