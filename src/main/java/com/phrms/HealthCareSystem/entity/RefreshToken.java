package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "refresh_tokens")
public class RefreshToken {
    @Id
    private String id;

    private String userId;
    private String token;
    private Date expiryDate;
    @Getter
    private boolean revoked;

    public RefreshToken(String userId, String token, Date expiryDate) {
        this.userId = userId;
        this.token = token;
        this.expiryDate = expiryDate;
        this.revoked = false;
    }

}
