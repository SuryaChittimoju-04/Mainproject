package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HospitalRepository extends MongoRepository<Hospital,String> {
    Optional<Hospital> findByName(String name);
}
