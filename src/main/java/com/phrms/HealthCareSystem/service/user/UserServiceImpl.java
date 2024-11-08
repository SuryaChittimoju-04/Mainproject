package com.phrms.HealthCareSystem.service.user;

import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.OTP;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.mapper.UserMapper;
import com.phrms.HealthCareSystem.model.LoginResponse;
import com.phrms.HealthCareSystem.model.OTPResponse;
import com.phrms.HealthCareSystem.repository.OTPRepository;
import com.phrms.HealthCareSystem.repository.UserRepository;
import com.phrms.HealthCareSystem.service.mail.MailService;
import com.phrms.HealthCareSystem.util.JWTUtil;
import com.phrms.HealthCareSystem.util.OTPGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private OTPGenerator otpGenerator;

    @Autowired
    private MailService mailService;

    @Override
    public void registerUser(UserDto userDto) throws Exception {
        if (patientExist(userDto.getAadharNumber())!=null){
            throw new Exception("User Already Exists");
        }
        User user = UserMapper.INSTANCE.toEntity(userDto);
        userRepository.save(user);
    }
    public User patientExist(String aadharNumber) {
        return userRepository.findByAadharNumber(aadharNumber).orElse(null);
    }

    @Override
    public String sendEmail(String AadharNumber) throws Exception {
        User user = patientExist(AadharNumber);
        if (user!=null){
            String otpValue = otpGenerator.generateOtp();
            OTP otp = new OTP();
            otp.setOtp(otpValue);
            otp.setEmail(user.getPatientEmail());
            otp.setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 5));
            otpRepository.save(otp);
            mailService.sendVerificationEmail(user.getPatientEmail(),"OTP for login to P.H.R.M.S", "Hi,"+user.getPatientName()+"!\n\nWe received your request for a OTP code to use with your P.H.R.M.S.\n\nYour OTP is: "+"\""+otp.getOtp()+"\"\n\nIf you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.");
            return user.getPatientEmail();
        }else {
            throw new Exception("Invalid Credentials");
        }
    }
}
