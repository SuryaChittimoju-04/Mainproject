package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Doctor {
    @Id
    private String id;
    private String specialization;
    private String hospitalId;
    private String name;

}
