package com.phrms.HealthCareSystem.entity;


import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "laboratory")
public class Laboratory {
    @Id
    private String id;
    private String name;
    private Boolean isApproved;
    private String cloudStorageLink;
    private String cloudStorageType;
    private String address;
    private String location;
    private List<String> connectedHospitals;
}
