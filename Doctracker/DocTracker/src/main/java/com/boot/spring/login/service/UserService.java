package com.boot.spring.login.service;

import java.time.LocalDateTime;
import java.util.List;

import com.boot.spring.login.entity.*;

public interface UserService {
	
	void saveUser(User user);

	void saveAppointment(Appointment appointment);

	List<String> getFieldList();
	
	public List<Doctor> findByField(String field);
	
	public List<Doctor> findByLoc(String field, float latlower, float latupper, float lnglower, float lngupper);

	int getUserId(String name);

	Patient getPatient(int id);

	List<Slot> getSlots(int docId, LocalDateTime dateTime);

	Slot getSlot(int id);

	List <Appointment> getUserAppointments(int id,LocalDateTime now);

	Doctor getDoctor(int id);

	void updatePatientCount(int id, int count);

	List<Appointment> checkDuplicateSlotPick(int slotId, int patientId);

	List<Appointment> getUserPrevAppointments(int id);

	Appointment getAppointmentById(int id);

	Slot getSlotByAppointmentId(int id);

	User getUserById(int id);

	void updateUser(String fn, String ln, String email, String pass,int id);

	List<User>checkUnique(String userName);
}
