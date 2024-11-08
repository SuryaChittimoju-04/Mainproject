package com.phrms.HealthCareSystem.service.specialist.doctor;

import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.model.DoctorResponse;

import java.util.List;

public interface DoctorService {
    void RegisterDoctor(Doctor doctor) throws Exception;
    List<DoctorResponse> getDoctorsList(String Specialization);
}
