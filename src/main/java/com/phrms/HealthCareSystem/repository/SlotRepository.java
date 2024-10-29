package com.phrms.HealthCareSystem.repository;

import com.phrms.HealthCareSystem.entity.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SlotRepository extends MongoRepository<Slot,String> {
    @Query("{ 'isBooked': false, 'startTime' : { $gte: ?0 } }")
    List<Slot> findAvailableSlots(Date currentTime);
    @Query("{ 'patientId': ?0, 'startTime' : { $gte: ?1 } }")
    List<Slot> findByPatientIdAndStartTimeAfter(String patientId, Date currentTime);
    @Query("{ 'doctorId': ?0, 'startTime' : { $gte: ?1 } }")
    List<Slot> findByDoctorIdAndStartTimeAfter(String doctorId, Date currentTime);
    Optional<Slot> findById(String Id);
}
