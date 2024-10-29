package com.phrms.HealthCareSystem.service.user;

import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;

public interface UserService {
    void registerUser(UserDto userDto)throws Exception;
    User patientExist(String AadharNumber);

}
