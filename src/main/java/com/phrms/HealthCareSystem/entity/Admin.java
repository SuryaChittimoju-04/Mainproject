package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    private String email;
    private String password;
    private String isActive;
    private String name;
}
