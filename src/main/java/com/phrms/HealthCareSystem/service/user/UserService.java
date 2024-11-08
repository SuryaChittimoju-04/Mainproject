package com.phrms.HealthCareSystem.service.user;

import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;

public interface UserService {
    void registerUser(UserDto userDto)throws Exception;
    User patientExist(String AadharNumber);
    String sendEmail(String AadharNumber) throws Exception;
}
