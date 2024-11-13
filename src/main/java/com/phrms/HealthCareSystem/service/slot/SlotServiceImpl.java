package com.phrms.HealthCareSystem.service.slot;

import com.phrms.HealthCareSystem.dto.BookSlot;
import com.phrms.HealthCareSystem.dto.CreateSlot;
import com.phrms.HealthCareSystem.entity.Slot;
import com.phrms.HealthCareSystem.model.SlotResponse;
import com.phrms.HealthCareSystem.repository.DoctorRepository;
import com.phrms.HealthCareSystem.repository.SlotRepository;
import com.phrms.HealthCareSystem.repository.SpecializationRepository;
import com.phrms.HealthCareSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService{

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Slot> availableSlots(String doctorId) {
        return slotRepository.findByStartTimeAfterAndDoctorId(new Date(),doctorId);
    }

    @Override
    public List<SlotResponse> bookedSlots(String userId, Boolean isDoc) {
        List<SlotResponse> slotResponseList;
        if(isDoc){
            slotResponseList = slotRepository.findByDoctorIdAndStartTimeAfter(userId,new Date())
                    .stream()
                    .map(slot -> {
                        SlotResponse response = new SlotResponse();
                        response.setName(userRepository.findById(slot.getPatientId()).get().getPatientName());
                        response.setSpecialization(specializationRepository.findById(doctorRepository.findById(slot.getDoctorId()).get().getSpecialization()).get().getSpecialization());
                        response.setStartTime(slot.getStartTime());
                        response.setEndTime(slot.getEndTime());
                        return response;
                    }).toList();
        }else {
            slotResponseList = slotRepository.findByPatientIdAndStartTimeAfter(userId,new Date())
                    .stream()
                    .map(slot -> {
                        SlotResponse response = new SlotResponse();
                        response.setName(doctorRepository.findById(slot.getDoctorId()).get().getName());
                        response.setSpecialization(specializationRepository.findById(doctorRepository.findById(slot.getDoctorId()).get().getSpecialization()).get().getSpecialization());
                        response.setStartTime(slot.getStartTime());
                        response.setEndTime(slot.getEndTime());
                        return response;
                    }).toList();
        }
        return slotResponseList;
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
    public void bookSlot(BookSlot bookSlot, String patientId) throws Exception{
        Slot slot = isSlotExists(bookSlot.getId());
        if (slot == null){
            throw new Exception("Invalid Slot");
        }
        slot.setPatientId(patientId);
        slot.setIsBooked(true);
        slotRepository.save(slot);
    }

    @Override
    public List<SlotResponse> history(String patientId) throws Exception {
        return slotRepository.findByPatientIdAndEndTimeBefore(patientId,new Date())
                .stream()
                .map(slot -> {
                    SlotResponse response = new SlotResponse();
                    response.setName(doctorRepository.findById(slot.getDoctorId()).get().getName());
                    response.setSpecialization(specializationRepository.findById(doctorRepository.findById(slot.getDoctorId()).get().getSpecialization()).get().getSpecialization());
                    response.setStartTime(slot.getStartTime());
                    response.setEndTime(slot.getEndTime());
                    return response;
                }).toList();
    }

    @Override
    public List<SlotResponse> docHistory(String doctorId) throws Exception {
        return slotRepository.findByDoctorIdAndEndTimeBefore(doctorId,new Date())
                .stream()
                .map(slot -> {
                    SlotResponse response = new SlotResponse();
                    response.setName(userRepository.findById(slot.getPatientId()).get().getPatientName());
                    response.setSpecialization(specializationRepository.findById(doctorRepository.findById(slot.getDoctorId()).get().getSpecialization()).get().getSpecialization());
                    response.setStartTime(slot.getStartTime());
                    response.setEndTime(slot.getEndTime());
                    return response;
                }).toList();
    }


    public Slot isSlotExists(String slotId){
        return slotRepository.findById(slotId).orElse(null);
    }
}
