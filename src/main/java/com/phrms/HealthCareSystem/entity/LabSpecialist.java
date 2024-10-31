package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "labSpecialist")
public class LabSpecialist {
    @Id
    private String id;
    private String laboratoryId;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String gender;
    private String specialization;
}
