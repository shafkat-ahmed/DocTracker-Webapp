package com.boot.spring.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Manager;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;
import com.boot.spring.login.repository.AppointmentRepository;
import com.boot.spring.login.repository.ManagerRepository;
import com.boot.spring.login.repository.SlotRepository;
import com.boot.spring.login.repository.UserRepository;

@Service
public class ManagerServiceImp implements ManagerService {
	
	@Autowired
	private SlotRepository slotRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private ManagerRepository managerRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void saveSlot(Slot slot) {
		// TODO Auto-generated method stub
		slotRepo.save(slot);

	}
	@Override
	public int getManId(String s) {
		// TODO Auto-generated method stub
		return managerRepo.getManId(s);
	}
	@Override
	public Manager getMan(int id) {
		// TODO Auto-generated method stub
		return managerRepo.getMan(id);
	}
	@Override
	public Slot getSlot(int docId) {
		// TODO Auto-generated method stub
		return managerRepo.getSlot(docId);
	}
	@Override
	public List<Slot> getAllSlot(int docId) {
		// TODO Auto-generated method stub
		return managerRepo.getAllSlot(docId);
	}
	@Override
	public Slot getUpdateSlot(int id) {
		// TODO Auto-generated method stub
		return managerRepo.getUpdateSlot(id);
	}
	@Override
	public void deleteSlotById(int id) {
		// TODO Auto-generated method stub
		slotRepo.deleteById(id);
		
	}
	@Override
	public List<Appointment> getAppointments(int id) {
		// TODO Auto-generated method stub
		return appointmentRepo.getAppointments(id);
	}
	@Override
	public User getPatient(int patientId) {
		// TODO Auto-generated method stub
		return userRepo.getById(patientId);
	}
}
