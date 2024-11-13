package com.phrms.HealthCareSystem.dto;

import lombok.Data;

@Data
public class ApproveManagementRequest {
    private String managementId;
    private Boolean isHospital;
}
