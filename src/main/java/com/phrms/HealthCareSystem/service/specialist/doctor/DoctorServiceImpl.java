package com.phrms.HealthCareSystem.service.specialist.doctor;

import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.model.DoctorResponse;
import com.phrms.HealthCareSystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public void RegisterDoctor(Doctor doctor) throws Exception{
        if (doctorRepository.findByEmail(doctor.getEmail()).isEmpty()){
            doctorRepository.save(doctor);
        }else {
            throw new Exception("Doctor Already Exist");
        }
    }

    @Override
    public List<DoctorResponse> getDoctorsList(String Specialization) {
        return doctorRepository.findBySpecialization(Specialization).stream()
                .map(doctor -> {
                    DoctorResponse doctorResponse = new DoctorResponse();
                    doctorResponse.setId(doctor.getId());
                    doctorResponse.setEmail(doctor.getEmail());
                    doctorResponse.setName(doctor.getName());
                    doctorResponse.setGender(doctor.getGender());
                    doctorResponse.setSpecialization(doctor.getSpecialization());
                    doctorResponse.setPhoneNumber(doctor.getPhoneNumber());
                    return doctorResponse;
                }).toList();
    }
}
