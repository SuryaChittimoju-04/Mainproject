package com.phrms.HealthCareSystem.service.management.hospital;

import com.phrms.HealthCareSystem.entity.Hospital;
import com.phrms.HealthCareSystem.entity.Specialization;
import com.phrms.HealthCareSystem.repository.HospitalRepository;
import com.phrms.HealthCareSystem.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService{
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public void RegisterHospital(Hospital hospitalRegisterRequest) throws Exception {
        if (hospitalRepository.findByEmail(hospitalRegisterRequest.getEmail()).isEmpty()){
            hospitalRepository.save(hospitalRegisterRequest);
        }else {
            throw new Exception("Hospital name Already taken");
        }
    }

    @Override
    public Hospital LoginHospital() {
        return null;
    }

    @Override
    public void addSpecialization(Specialization specialization) throws Exception {
        if (specializationRepository.findBySpecialization(specialization.getSpecialization()).isEmpty()){
            specializationRepository.save(specialization);
        }else {
            throw new Exception("Specialization Already Exist");
        }
    }

    @Override
    public List<Specialization> specilizationsList() {
        return specializationRepository.findAll().stream().toList();
    }
}
