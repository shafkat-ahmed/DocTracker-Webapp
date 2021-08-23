package com.boot.spring.login.service;

import java.util.List;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Doctor;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;

public interface DoctorService {

	int getId(String name);

	int getDocId(int id);

	Doctor getDoctorById(int docId);

	List<Slot> getDocSlot(int docId);

	List<Appointment> getAppointments(int slotId);

	Appointment getAppointmentById(int appId);

	void saveAppointment(Appointment appointment);

	User getPatient(int patientId);

}
