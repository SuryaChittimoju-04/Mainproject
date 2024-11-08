package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hospitals")
@Data
public class Hospital {
    @Id
    private String id;
    private String name;
    private Boolean isApproved;
    private String dbLink;
    private String dbType;
    private String email;
    private String password;
    private String address;
    private String location;
    private Infra infra;
    @Data
    public class Infra {
        private String totalBeds;
        private String availableBeds;
    }
}
