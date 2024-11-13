package com.phrms.HealthCareSystem.service.specialist.doctor;

import com.phrms.HealthCareSystem.dto.DoctorLoginRequest;
import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.entity.LabSpecialist;
import com.phrms.HealthCareSystem.model.DoctorLoginResponse;
import com.phrms.HealthCareSystem.model.DoctorResponse;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.repository.DoctorRepository;
import com.phrms.HealthCareSystem.repository.LabSpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private LabSpecialistRepository labSpecialistRepository;

    @Override
    public void RegisterDoctor(Doctor doctor) throws Exception {
        if (doctorRepository.findByEmail(doctor.getEmail()).isEmpty()) {
            doctorRepository.save(doctor);
        } else {
            throw new Exception("Doctor Already Exist");
        }
    }

    @Override
    public DoctorLoginResponse doctorLogin(DoctorLoginRequest loginRequest) throws Exception {
        if (loginRequest.getIsDoc()){
            Optional<Doctor> doctor = doctorRepository.findByEmail(loginRequest.getEmail());
            if (doctor.isEmpty()) {
                throw new Exception("Invalid email");
            } else {
                if (doctor.get().getPassword().equals(loginRequest.getPassword()) && doctor.get().getHospitalId().equals(loginRequest.getManagementId())) {
                    DoctorLoginResponse loginResponse = new DoctorLoginResponse();
                    loginResponse.setId(doctor.get().getId());
                    loginResponse.setName(doctor.get().getName());
                    loginResponse.setIsDoc(loginRequest.getIsDoc());
                    return loginResponse;
                } else {
                    throw new Exception("Invalid Credentials");
                }
            }
        }else {
            Optional<LabSpecialist> labSpecialist = labSpecialistRepository.findByEmail(loginRequest.getEmail());
            if (labSpecialist.isEmpty()){
                throw new Exception("Invalid email");
            }else {
                if (labSpecialist.get().getPassword().equals(loginRequest.getPassword()) && labSpecialist.get().getLaboratoryId().equals(loginRequest.getManagementId())){
                    DoctorLoginResponse loginResponse = new DoctorLoginResponse();
                    loginResponse.setId(labSpecialist.get().getId());
                    loginResponse.setName(labSpecialist.get().getName());
                    loginResponse.setIsDoc(loginRequest.getIsDoc());
                    return loginResponse;
                }else {
                    throw new Exception("Invalid Credentials");
                }
            }
        }
    }

    @Override
    public void registerLab(LabSpecialist labSpecialist) throws Exception {
        if (labSpecialistRepository.findByEmail(labSpecialist.getEmail()).isEmpty()) {
            labSpecialistRepository.save(labSpecialist);
        } else {
            throw new Exception("Lab Specialist Already Exist");
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
