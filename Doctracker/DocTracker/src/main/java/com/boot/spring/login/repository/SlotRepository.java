package com.boot.spring.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Slot;

public interface SlotRepository  extends JpaRepository<Slot, Integer> {
	
	@Query("SELECT s from Slot s where s.docId=:docId")
	List<Slot> getDocSlot(@Param("docId") int docId);

	@Query("SELECT a from Appointment a where a.slotId=:slotId")
	List<Appointment> getAppointments(@Param("slotId")int slotId);
		
}
