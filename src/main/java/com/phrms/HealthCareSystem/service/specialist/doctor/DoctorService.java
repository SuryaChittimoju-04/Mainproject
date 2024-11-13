package com.phrms.HealthCareSystem.service.specialist.doctor;

import com.phrms.HealthCareSystem.dto.DoctorLoginRequest;
import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.entity.LabSpecialist;
import com.phrms.HealthCareSystem.model.DoctorLoginResponse;
import com.phrms.HealthCareSystem.model.DoctorResponse;

import java.util.List;

public interface DoctorService {
    void RegisterDoctor(Doctor doctor) throws Exception;
    DoctorLoginResponse doctorLogin(DoctorLoginRequest loginRequest) throws Exception;
    void registerLab(LabSpecialist labSpecialist) throws Exception;
    List<DoctorResponse> getDoctorsList(String Specialization);
}
