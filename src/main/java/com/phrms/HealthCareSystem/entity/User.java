package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection ="user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String aadharNumber;
    private String patientPhNumber;
    private String patientEmail;
    private String patientName;
    private String patientAge;
    private String patientGender;
}
