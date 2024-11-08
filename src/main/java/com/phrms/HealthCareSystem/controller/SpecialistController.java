package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.entity.Specialization;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.model.DoctorResponse;
import com.phrms.HealthCareSystem.service.management.hospital.HospitalService;
import com.phrms.HealthCareSystem.service.specialist.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("HCS/specialist")
public class SpecialistController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/register/doctor")
    public ResponseEntity<ApiResponse> registerDoctor(@RequestBody Doctor doctor){
        try {
            doctorService.RegisterDoctor(doctor);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registered Successfully",doctor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error occured",e.getMessage()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Logged in Successfully",""));
    }
    @GetMapping("/specializations")
    public ResponseEntity<ApiResponse> specializationsList(){
        try{
            List<Specialization> specializations = hospitalService.specilizationsList();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Available Specializations",specializations));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error Occured",e.getMessage()));
        }
    }
    @GetMapping("/doctors")
    public ResponseEntity<ApiResponse> doctorsList(@RequestParam String specialization) {
        try{
            List<DoctorResponse> doctorResponses = doctorService.getDoctorsList(specialization);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"List of Doctors",doctorResponses));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Some Error Occured",e.getMessage()));
        }
    }
    @GetMapping("/reports")
    public ResponseEntity<ApiResponse> reports(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
    @PostMapping("/newReport")
    public ResponseEntity<ApiResponse> uploadReport(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
}
