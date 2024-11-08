package com.phrms.HealthCareSystem.controller;

import com.phrms.HealthCareSystem.dto.BookSlot;
import com.phrms.HealthCareSystem.dto.CreateSlot;
import com.phrms.HealthCareSystem.entity.Slot;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.model.ApiResponse;
import com.phrms.HealthCareSystem.service.slot.SlotService;
import com.phrms.HealthCareSystem.service.user.UserService;
import com.phrms.HealthCareSystem.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/HCS/slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;
    @GetMapping("/available")
    public ResponseEntity<ApiResponse> availableSlots(){
        List<Slot> availableSlots = slotService.availableSlots();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Available Slots",availableSlots));
    }

    @GetMapping("/booked")
    public ResponseEntity<ApiResponse> bookedSlot(@RequestHeader String Authorization){
        String aadharNumber = jwtUtil.extractUsername(Authorization.substring(7));
        User user = userService.patientExist(aadharNumber);
        List<Slot> bookedSlots = slotService.bookedSlots(user.getId(),false);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Appointments",bookedSlots));
    }

    @PostMapping("/book")
    public ResponseEntity<ApiResponse> bookSlot(@RequestBody BookSlot bookSlot){
        try{
            slotService.bookSlot(bookSlot);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"Slot Booking Successful",""));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(500,"Slot Booking Failed",e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSlot(@RequestBody CreateSlot createSlot){
        slotService.createSlot(createSlot);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(200,"",""));
    }
}
