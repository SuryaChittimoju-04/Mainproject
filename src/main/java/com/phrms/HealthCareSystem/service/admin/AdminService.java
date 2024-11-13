package com.phrms.HealthCareSystem.service.admin;

import com.phrms.HealthCareSystem.dto.AdminLoginRequest;
import com.phrms.HealthCareSystem.dto.ApproveManagementRequest;
import com.phrms.HealthCareSystem.entity.Admin;
import com.phrms.HealthCareSystem.model.AdminLoginResponse;
import com.phrms.HealthCareSystem.model.NewRegistrationsResponse;

import java.util.List;

public interface AdminService {
    void RegisterAdmin(Admin admin) throws Exception;

    AdminLoginResponse loginAdmin(AdminLoginRequest loginRequest) throws Exception;

    List<NewRegistrationsResponse> newRegistrations() throws Exception;
    void approveRegistration(ApproveManagementRequest management) throws Exception;
}
