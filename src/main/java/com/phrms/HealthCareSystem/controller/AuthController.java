package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.LoginRequest;
import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/HCS/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse>login(@RequestBody LoginRequest loginRequest)throws Exception{
        try{
            User user = userService.patientExist(loginRequest.getAadharNumber());
            if (user==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((new ApiResponse(500,"InternalSerive Error Occured","Surya")));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"login Successful",user.getPatientName()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((new ApiResponse(500,"InternalSerive Error Occured","Surya")));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDto userDto)throws Exception{
        try {
            userService.registerUser(userDto);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((new ApiResponse(500,"InternalSerive Error Occured","Surya")));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Registerd Succesfully",userDto.getPatientName()));
    }
}
