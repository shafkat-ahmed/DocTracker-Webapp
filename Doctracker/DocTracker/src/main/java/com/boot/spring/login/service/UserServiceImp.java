package com.boot.spring.login.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.boot.spring.login.entity.*;
import com.boot.spring.login.repository.AppointmentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.spring.login.repository.UserRepository;
@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
       userRepository.save(user);
       
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}


	@Override
	public List<String> getFieldList() {
		
		List <String> list=userRepository.getFieldList();
		
		return list;				
	}
	
	public List<Doctor> findByField(String field){
		List <Doctor> list=userRepository.findByField(field);
		
		return list;
	}

	@Override
	public List<Doctor> findByLoc(String field, float latlower, float latupper, float lnglower, float lngupper) {
		
		List <Doctor> list=userRepository.findByLoc(field, latlower, latupper, lnglower, lngupper);
		
		return list;
	}

	@Override
	public int getUserId(String name) {
		int id =userRepository.getUserId(name);
		return id;
	}

	@Override
	public Patient getPatient(int id) {
		Patient patient=userRepository.getPatient(id);
		return patient;
	}

	@Override
	public List<Slot> getSlots(int docId, LocalDateTime dateTime) {
		List<Slot> slots=userRepository.getSlots(docId,dateTime);
		return slots;
	}

	@Override
	public Slot getSlot(int id) {
		Slot slot=userRepository.getSlot(id);
		return slot;
	}

	@Override
	public List<Appointment> getUserAppointments(int id,LocalDateTime now) {
		List <Appointment> appointments=appointmentRepository.getUserAppointments(id,now);
		return appointments;
	}

	@Override
	public Doctor getDoctor(int id) {
		Doctor doctor=userRepository.getDoctor(id);
		return doctor;
	}

	@Override
	public void updatePatientCount(int id, int count) {
		int i=appointmentRepository.updatePatientCount(id, count);
	}

	@Override
	public List<Appointment> checkDuplicateSlotPick(int slotId, int patientId) {
		List<Appointment>list=appointmentRepository.checkDuplicateSlotPick(slotId,patientId);
		return list;
	}

	@Override
	public List<Appointment> getUserPrevAppointments(int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.getUserPrevAppointments(id);
	}

	@Override
	public Appointment getAppointmentById(int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.getById(id);
	}

	@Override
	public Slot getSlotByAppointmentId(int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.getSlotByAppointmentId(id);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	}

	@Override
	public void updateUser(String fn, String ln, String email, String pass,int id) {
		// TODO Auto-generated method stub
	     userRepository.updateUser(fn,ln,email,pass,id);
		
	}

	@Override
	public List<User> checkUnique(String userName) {
		return userRepository.checkUnique(userName);
	}


}
