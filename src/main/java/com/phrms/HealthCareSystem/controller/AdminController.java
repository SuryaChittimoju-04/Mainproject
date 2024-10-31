package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HCS/admin")
public class AdminController {
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> adminLogin(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Logged in Successfully",""));
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
