package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.Specialization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpecializationRepository extends MongoRepository<Specialization,String> {
    Optional<Specialization> findBySpecialization(String specialization);
}
