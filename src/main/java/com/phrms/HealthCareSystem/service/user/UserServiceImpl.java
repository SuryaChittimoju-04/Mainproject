package com.phrms.HealthCareSystem.service.user;

import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.mapper.UserMapper;
import com.phrms.HealthCareSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserDto userDto) throws Exception {
        if (patientExist(userDto.getAadharNumber())!=null){
            throw new Exception();
        }
        User user = UserMapper.INSTANCE.toEntity(userDto);
        userRepository.save(user);
    }
    public User patientExist(String aadharNumber) {
        return userRepository.findByAadharNumber(aadharNumber).orElse(null);
    }
}
