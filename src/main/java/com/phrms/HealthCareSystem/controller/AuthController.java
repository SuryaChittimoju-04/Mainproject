package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.LoginRequest;
import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;
import com.phrms.HealthCareSystem.service.auth.AuthService;
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

    @Autowired
    AuthService authService;

    @PostMapping("/otpVerification")
    public ResponseEntity<ApiResponse> otpVerification(@RequestBody OTPResponse otpResponse)throws Exception{
        try{
            LoginResponse loginResponse = authService.verifyEmail(otpResponse);
            if (loginResponse.getAccess_token().isBlank()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500,"Invalid OTP!","Invalid OTP"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"OTP Verified!",loginResponse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500,"Internal Server Error",e.getMessage()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest)throws Exception{
        try{
            String email = userService.sendEmail(loginRequest.getAadharNumber());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"OTP Sent Successful",email));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((new ApiResponse(500,"InternalSerive Error Occured",e.getMessage())));
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
