package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.AdminLoginRequest;
import com.phrms.HealthCareSystem.entity.Admin;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HCS/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Logged in Successfully",""));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Something Went Wrong",e.getMessage()));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> adminRegister(@RequestBody Admin admin){
        try {
            adminService.RegisterAdmin(admin);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registered Successful",admin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(500,"Something Went Wrong",e.getMessage()));
        }
    }
    @PostMapping("/validateRegistration")
    public ResponseEntity<ApiResponse> validateRegistration(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
    @GetMapping("/getNewRegistrations")
    public ResponseEntity<ApiResponse> getNewRegistrations(){

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
}
