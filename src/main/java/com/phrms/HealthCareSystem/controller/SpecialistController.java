package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("HCS/specialist")
public class SpecialistController {
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registered Successfully",""));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Logged in Successfully",""));
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
