package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.ManagementLoginRequest;
import com.phrms.HealthCareSystem.entity.Hospital;
import com.phrms.HealthCareSystem.entity.Laboratory;
import com.phrms.HealthCareSystem.entity.Specialization;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.model.ManagementLoginResponse;
import com.phrms.HealthCareSystem.service.admin.AdminService;
import com.phrms.HealthCareSystem.service.management.hospital.HospitalService;
import com.phrms.HealthCareSystem.service.management.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HCS/management")
public class ManagementController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private LabService labService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/register/hospital")
    public ResponseEntity<ApiResponse> registerHospital(@RequestBody Hospital hospitalRegisterRequest){
        try {
            hospitalService.RegisterHospital(hospitalRegisterRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registration Successful",hospitalRegisterRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error occured",e.getMessage()));
        }
    }
    @PostMapping("/register/lab")
    public ResponseEntity<ApiResponse> registerLab(@RequestBody Laboratory laboratory){
        try{
            labService.registerLab(laboratory);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registration Successful",laboratory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error occured",e.getMessage()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody ManagementLoginRequest managementLoginRequest){
        try{
            ManagementLoginResponse loginResponse = adminService.managementLogin(managementLoginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Logged in Successfully",loginResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500,"Error Occured",e.getMessage()));
        }
    }
    @PostMapping("/hospital/addSpecialization")
    public ResponseEntity<ApiResponse> addSpecialization(@RequestBody Specialization specialization){
        try {
            hospitalService.addSpecialization(specialization);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Specialization Added Successfully",specialization));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error occured",e.getMessage()));
        }
    }
    @GetMapping("/employees")
    public ResponseEntity<ApiResponse> getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
    @PostMapping("/validateRequest")
    public ResponseEntity<ApiResponse> validateRequest(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
}
