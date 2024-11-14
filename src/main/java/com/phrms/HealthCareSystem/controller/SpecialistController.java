package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.DoctorLoginRequest;
import com.phrms.HealthCareSystem.dto.ReportRequest;
import com.phrms.HealthCareSystem.entity.Doctor;
import com.phrms.HealthCareSystem.entity.LabSpecialist;
import com.phrms.HealthCareSystem.entity.Specialization;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.model.DoctorLoginResponse;
import com.phrms.HealthCareSystem.model.DoctorResponse;
import com.phrms.HealthCareSystem.model.ReportsResponse;
import com.phrms.HealthCareSystem.service.management.hospital.HospitalService;
import com.phrms.HealthCareSystem.service.specialist.doctor.DoctorService;
import com.phrms.HealthCareSystem.service.user.UserService;
import com.phrms.HealthCareSystem.util.JWTUtil;
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
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/register/doctor")
    public ResponseEntity<ApiResponse> registerDoctor(@RequestBody Doctor doctor) {
        try {
            doctorService.RegisterDoctor(doctor);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Registered Successfully", doctor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Some Error occured", e.getMessage()));
        }
    }

    @PostMapping("/register/lab")
    public ResponseEntity<ApiResponse> registerLabSpecialist(@RequestBody LabSpecialist labSpecialist) {
        try {
            doctorService.registerLab(labSpecialist);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Registered Successfully", labSpecialist));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Some Error Occured", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody DoctorLoginRequest loginRequest) {
        try {
            DoctorLoginResponse doctorLoginResponse = doctorService.doctorLogin(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Logged in Successfully", doctorLoginResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Invalid Credentials", "Please Check your details"));
        }
    }

    @GetMapping("/specializations")
    public ResponseEntity<ApiResponse> specializationsList() {
        try {
            List<Specialization> specializations = hospitalService.specilizationsList();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Available Specializations", specializations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Some Error Occured", e.getMessage()));
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<ApiResponse> doctorsList(@RequestParam String specialization) {
        try {
            List<DoctorResponse> doctorResponses = doctorService.getDoctorsList(specialization);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "List of Doctors", doctorResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Some Error Occured", e.getMessage()));
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<ApiResponse> reports(@RequestHeader String Authorization) {
        String aadharNumber = jwtUtil.extractUsername(Authorization.substring(7));
        User user = userService.patientExist(aadharNumber);
        List<ReportsResponse> responseList = doctorService.getReports(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Medical Reports", responseList));
    }

    @PostMapping("/newReport")
    public ResponseEntity<ApiResponse> uploadReport(@RequestBody ReportRequest reportRequest) {
        try {
            doctorService.uploadReport(reportRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200, "Report Uploaded Successfully", reportRequest));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500,"Something went wrong",e.getMessage()));
        }
    }
}
