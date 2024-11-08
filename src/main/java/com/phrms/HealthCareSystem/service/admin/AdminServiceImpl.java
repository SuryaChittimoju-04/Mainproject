package com.phrms.HealthCareSystem.service.admin;

import com.phrms.HealthCareSystem.entity.Admin;
import com.phrms.HealthCareSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public void RegisterAdmin(Admin admin) throws Exception {
        if (adminRepository.findByEmail(admin.getEmail()).isEmpty()){
            adminRepository.save(admin);
        }else {
            throw new Exception("Admin Already Exist");
        }
    }
}
