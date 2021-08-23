package com.boot.spring.login.service;

import java.util.List;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Manager;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;

public interface ManagerService {
	void saveSlot(Slot slot);
	int getManId(String s);
	Manager getMan(int id);
	Slot getSlot(int docId);
	List<Slot> getAllSlot(int docId);
	Slot getUpdateSlot(int id);
	void deleteSlotById(int id);
	List<Appointment> getAppointments(int id);
	User getPatient(int patientId);

}
