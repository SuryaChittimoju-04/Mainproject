package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class Report {
    @Id
    private String id;
    private String patientId;
    private String name;
    private String image;
}
