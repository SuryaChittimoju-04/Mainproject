package com.phrms.HealthCareSystem.service.slot;

import com.phrms.HealthCareSystem.dto.BookSlot;
import com.phrms.HealthCareSystem.dto.CreateSlot;
import com.phrms.HealthCareSystem.entity.Slot;
import com.phrms.HealthCareSystem.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService{

    @Autowired
    SlotRepository slotRepository;
    @Override
    public List<Slot> availableSlots() {
        return slotRepository.findAvailableSlots(new Date());
    }

    @Override
    public List<Slot> bookedSlots(String userId, Boolean isDoc) {
        if(isDoc){
            return slotRepository.findByDoctorIdAndStartTimeAfter(userId,new Date());
        }else {
            return slotRepository.findByPatientIdAndStartTimeAfter(userId,new Date());
        }
    }

    @Override
    public void createSlot(CreateSlot createSlot) {
        Slot slot = new Slot();
        slot.setStartTime(createSlot.getStartTime());
        slot.setEndTime(createSlot.getEndTime());
        slot.setIsBooked(false);
        slotRepository.save(slot);
    }

    @Override
    public void bookSlot(BookSlot bookSlot) throws Exception{
        Slot slot = isSlotExists(bookSlot.getId());
        if (slot == null){
            throw new Exception("Invalid Slot");
        }
        slot.setPatientId(bookSlot.getPatientId());
        slot.setDoctorId(bookSlot.getDoctorId());
        slot.setIsBooked(true);
        slotRepository.save(slot);
    }

    public Slot isSlotExists(String slotId){
        return slotRepository.findById(slotId).orElse(null);
    }
}
