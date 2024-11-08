package com.phrms.HealthCareSystem.service.management.hospital;

import com.phrms.HealthCareSystem.entity.Hospital;
import com.phrms.HealthCareSystem.entity.Specialization;

import java.util.List;

public interface HospitalService {
    void RegisterHospital(Hospital hospitalRegisterRequest) throws Exception;
    Hospital LoginHospital();
    void addSpecialization(Specialization specialization) throws Exception;
    List<Specialization> specilizationsList();
}
