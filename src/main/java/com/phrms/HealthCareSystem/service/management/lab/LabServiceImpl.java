package com.phrms.HealthCareSystem.service.management.lab;

import com.phrms.HealthCareSystem.entity.Laboratory;
import com.phrms.HealthCareSystem.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Override
    public void registerLab(Laboratory laboratory) throws Exception {
        if (labRepository.findByEmail(laboratory.getEmail()).isEmpty()){
            labRepository.save(laboratory);
        }else {
            throw new Exception("Laboratory Already Exists");
        }
    }
}
