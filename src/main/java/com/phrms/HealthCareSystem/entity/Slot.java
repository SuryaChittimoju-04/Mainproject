package com.phrms.HealthCareSystem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "slots")
public class Slot {
    @Id
    private String id;
    private String patientId;
    private String doctorId;
    private Date startTime;
    private Date endTime;
    private Boolean isBooked;
}
