package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByAadharNumber(String AadharNumber);
}
