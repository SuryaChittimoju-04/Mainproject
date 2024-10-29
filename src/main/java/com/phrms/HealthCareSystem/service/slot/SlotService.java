package com.phrms.HealthCareSystem.service.slot;

import com.phrms.HealthCareSystem.dto.BookSlot;
import com.phrms.HealthCareSystem.dto.CreateSlot;
import com.phrms.HealthCareSystem.entity.Slot;

import java.util.List;

public interface SlotService {
    List<Slot> availableSlots();
    List<Slot> bookedSlots(String userId,Boolean isDoc);
    void createSlot(CreateSlot createSlot);
    void bookSlot(BookSlot bookSlot) throws Exception;
}
