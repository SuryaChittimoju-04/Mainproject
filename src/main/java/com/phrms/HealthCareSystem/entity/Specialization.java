package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "specializations")
public class Specialization {
    @Id
    private String id;
    @Indexed(unique = true)
    private String specialization;
    private String description;
}
