package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;
    private String hospitalId;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String gender;
    private String specialization;
    private Boolean isAffiliated;
    private List<String> labs;
}
