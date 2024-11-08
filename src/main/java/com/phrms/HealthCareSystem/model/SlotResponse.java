package com.phrms.HealthCareSystem.model;

import lombok.Data;

import java.util.Date;

@Data
public class SlotResponse {
    private String name;
    private String specialization;
    private Date startTime;
    private Date endTime;
}
