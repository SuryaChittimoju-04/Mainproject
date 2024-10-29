package com.phrms.HealthCareSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateSlot {
    private Date startTime;
    private Date endTime;
}
