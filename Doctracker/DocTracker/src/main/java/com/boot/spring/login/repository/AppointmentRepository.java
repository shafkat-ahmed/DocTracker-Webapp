package com.boot.spring.login.repository;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Doctor;
import com.boot.spring.login.entity.Patient;
import com.boot.spring.login.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a from Appointment a where a.patientId =:id AND a.slotId IN (SELECT s.id from Slot s where s.startTime> :now) ")
    List<Appointment> getUserAppointments(@Param("id") int id,@Param("now") LocalDateTime now);

    @Query("SELECT a from Appointment a where a.patientId =:patientId AND a.slotId=:slotId ")
    List<Appointment> checkDuplicateSlotPick(@Param("slotId") int slotId, @Param("patientId") int patientId);

    @Modifying
    @Transactional
    @Query("UPDATE Slot s SET s.patientCount =:count WHERE s.id=:id")
    int updatePatientCount(@Param("id") int id, @Param("count") int count);
    
    @Query("SELECT a from Appointment a where a.slotId=:slotId AND a.prescription IS NULL")
	List<Appointment> getAppointments(@Param("slotId")int slotId);

    @Query("SELECT a from Appointment a where a.patientId=:id AND a.prescription IS NOT NULL")
	List<Appointment> getUserPrevAppointments(@Param("id") int id);

    @Query("SELECT s from Slot s where s.id IN (SELECT a.slotId from Appointment a where a.id=:id)")
	Slot getSlotByAppointmentId(@Param("id") int id);
}
