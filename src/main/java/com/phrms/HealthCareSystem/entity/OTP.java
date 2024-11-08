package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "otp")
public class OTP {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String otp;
    private Date expiresAt;
}
