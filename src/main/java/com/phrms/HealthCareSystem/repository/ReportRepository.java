package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report,String> {
}
