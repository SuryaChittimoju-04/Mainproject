package com.phrms.HealthCareSystem.service.specialist.doctor;

import com.phrms.HealthCareSystem.dto.DoctorLoginRequest;
import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.model.DoctorLoginResponse;
import com.phrms.HealthCareSystem.model.DoctorResponse;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public DoctorLoginResponse doctorLogin(DoctorLoginRequest loginRequest) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findByEmail(loginRequest.getEmail());
        if (doctor.isEmpty()){
            throw new Exception("Invalid email");
        }else {
            if (doctor.get().getPassword().equals(loginRequest.getPassword()) && doctor.get().getHospitalId().equals(loginRequest.getHospitalId())){
                DoctorLoginResponse loginResponse = new DoctorLoginResponse();
                loginResponse.setId(doctor.get().getId());
                loginResponse.setName(doctor.get().getName());
                return loginResponse;
            }else {
                throw new Exception("Invalid Credentials");
            }
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
