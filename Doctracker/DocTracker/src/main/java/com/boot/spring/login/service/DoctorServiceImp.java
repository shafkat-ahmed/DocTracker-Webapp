package com.boot.spring.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Doctor;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;
import com.boot.spring.login.repository.AppointmentRepository;
import com.boot.spring.login.repository.DoctorRepository;
import com.boot.spring.login.repository.SlotRepository;
import com.boot.spring.login.repository.UserRepository;

@Service
public class DoctorServiceImp implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private SlotRepository slotRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private   UserRepository userRepo;
	
	
	@Override
	public int getId(String name) {
		// TODO Auto-generated method stub
		return doctorRepo.getId(name);
	}

	@Override
	public int getDocId(int id) {
		// TODO Auto-generated method stub
		return doctorRepo.getDocId(id);
	}

	@Override
	public Doctor getDoctorById(int docId) {
		// TODO Auto-generated method stub
		return doctorRepo.getById(docId);
	}

	@Override
	public List<Slot> getDocSlot(int docId) {
		// TODO Auto-generated method stub
		return slotRepo.getDocSlot(docId);
	}

	@Override
	public List<Appointment> getAppointments(int slotId) {
		// TODO Auto-generated method stub
		return appointmentRepo.getAppointments(slotId);
	}

	@Override
	public Appointment getAppointmentById(int appId) {
		// TODO Auto-generated method stub
		return appointmentRepo.getById(appId);
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentRepo.save(appointment);
	}

	@Override
	public User getPatient(int patientId) {
		// TODO Auto-generated method stub
		return userRepo.getById(patientId);
	}

}
