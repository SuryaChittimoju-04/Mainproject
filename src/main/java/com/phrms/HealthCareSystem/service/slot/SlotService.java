package com.phrms.HealthCareSystem.service.slot;

import com.phrms.HealthCareSystem.dto.BookSlot;
import com.phrms.HealthCareSystem.dto.CreateSlot;
import com.phrms.HealthCareSystem.entity.Slot;
import com.phrms.HealthCareSystem.model.SlotResponse;

import java.util.List;

public interface SlotService {
    List<Slot> availableSlots(String doctorId);
    List<SlotResponse> bookedSlots(String userId, Boolean isDoc);
    void createSlot(CreateSlot createSlot);
    void bookSlot(BookSlot bookSlot,String patientId) throws Exception;
    List<SlotResponse> history(String patientId) throws Exception;
    List<SlotResponse> docHistory(String doctorId) throws Exception;
}
