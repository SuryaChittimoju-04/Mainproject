package com.phrms.HealthCareSystem.service.admin;

import com.phrms.HealthCareSystem.dto.AdminLoginRequest;
import com.phrms.HealthCareSystem.dto.ApproveManagementRequest;
import com.phrms.HealthCareSystem.dto.ManagementLoginRequest;
import com.phrms.HealthCareSystem.entity.Admin;
import com.phrms.HealthCareSystem.entity.Hospital;
import com.phrms.HealthCareSystem.entity.Laboratory;
import com.phrms.HealthCareSystem.model.AdminLoginResponse;
import com.phrms.HealthCareSystem.model.ManagementLoginResponse;
import com.phrms.HealthCareSystem.model.NewRegistrationsResponse;
import com.phrms.HealthCareSystem.repository.AdminRepository;
import com.phrms.HealthCareSystem.repository.HospitalRepository;
import com.phrms.HealthCareSystem.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private LabRepository labRepository;

    @Override
    public void RegisterAdmin(Admin admin) throws Exception {
        if (adminRepository.findByEmail(admin.getEmail()).isEmpty()) {
            adminRepository.save(admin);
        } else {
            throw new Exception("Admin Already Exist");
        }
    }

    @Override
    public AdminLoginResponse loginAdmin(AdminLoginRequest loginRequest) throws Exception {
        Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
        if (admin.isEmpty()) {
            throw new Exception("Invalid Email");
        } else {
            if (admin.get().getPassword().equals(loginRequest.getPassword())) {
                AdminLoginResponse adminLoginResponse = new AdminLoginResponse();
                adminLoginResponse.setId(adminLoginResponse.getId());
                adminLoginResponse.setName(admin.get().getName());
                return adminLoginResponse;
            } else {
                throw new Exception("Invalid Credentials");
            }
        }
    }

    @Override
    public List<NewRegistrationsResponse> newRegistrations() throws Exception {
        List<NewRegistrationsResponse> responseList = new ArrayList<>();

        // Fetch all hospitals
        List<Hospital> hospitalList = hospitalRepository.findAll();
        responseList.addAll(
                hospitalList.stream()
                        .filter(hospital -> !hospital.getIsApproved()) // Only not approved hospitals
                        .map(hospital -> {
                            HashMap<String, Object> res = new HashMap<>();
                            res.put("id", hospital.getId());
                            res.put("name", hospital.getName());
                            res.put("isApproved",hospital.getIsApproved());
                            res.put("location", hospital.getLocation()); // Assuming location exists in Hospital

                            NewRegistrationsResponse response = new NewRegistrationsResponse();
                            response.setManagements(res);
                            return response;
                        })
                        .toList()
        );

        // Fetch all laboratories
        List<Laboratory> laboratoryList = labRepository.findAll();
        responseList.addAll(
                laboratoryList.stream()
                        .filter(laboratory -> !laboratory.getIsApproved()) // Only not approved laboratories
                        .map(laboratory -> {
                            HashMap<String, Object> res = new HashMap<>();
                            res.put("id", laboratory.getId());
                            res.put("name", laboratory.getName());
                            res.put("isApproved",laboratory.getIsApproved());
                            res.put("location", laboratory.getLocation()); // Assuming location exists in Laboratory

                            NewRegistrationsResponse response = new NewRegistrationsResponse();
                            response.setManagements(res);
                            return response;
                        })
                        .toList()
        );

        return responseList;
    }

    @Override
    public void approveRegistration(ApproveManagementRequest management) throws Exception{
        if (management.getIsHospital()){
            Optional<Hospital> hospital = hospitalRepository.findById(management.getManagementId());
            if (hospital.isPresent()){
                Hospital hs = hospital.get();
                hs.setIsApproved(true);
                hospitalRepository.save(hs);
            }else {
                throw new Exception("No Hospital Found");
            }
        }else {
            Optional<Laboratory> laboratory = labRepository.findById(management.getManagementId());
            if (laboratory.isPresent()){
                Laboratory lab = laboratory.get();
                lab.setIsApproved(true);
                labRepository.save(lab);
            }else {
                throw new Exception("No Lab Found");
            }
        }
    }

    @Override
    public ManagementLoginResponse managementLogin(ManagementLoginRequest managementLoginRequest) throws Exception {
        if (managementLoginRequest.getIsHospital()){
            Optional<Hospital> hospital = hospitalRepository.findByEmail(managementLoginRequest.getEmail());
            if (hospital.isPresent()){
                Hospital hos = hospital.get();
                if (hos.getPassword().equals(managementLoginRequest.getPassword())){
                    ManagementLoginResponse response = new ManagementLoginResponse();
                    response.setId(hos.getId());
                    response.setIsHospital(true);
                    response.setName(hos.getName());
                    return response;
                }else {
                    throw new Exception("Invalid Credentials");
                }
            } else {
                throw new Exception("Email Not Found");
            }
        }else {
            Optional<Laboratory> laboratory = labRepository.findByEmail(managementLoginRequest.getEmail());
            if (laboratory.isPresent()){
                Laboratory lab = laboratory.get();
                if (lab.getPassword().equals(managementLoginRequest.getPassword())){
                    ManagementLoginResponse response = new ManagementLoginResponse();
                    response.setId(lab.getId());
                    response.setIsHospital(false);
                    response.setName(lab.getName());
                    return response;
                }else {
                    throw new Exception("Invalid Credentials");
                }
            } else {
                throw new Exception("Email Not Found");
            }
        }
    }
}
