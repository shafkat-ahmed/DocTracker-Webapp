package com.boot.spring.login.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="patient_id")
	private int patientId;
	
	@Column(name="des")
	private String des;
	
	@Column(name="slot_id")
	private int slotId;

	@Column(name="contact_number")
	private int contactNumber;
	
	@Column(name="prescription")
	private String prescription;

//	private Patient patient;
//	private Slot slot;

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Appointment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}


	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setSlotIdFromString(String slotId) {
		int sId=Integer.parseInt(slotId);
		this.slotId = sId;
	}
	public void setPatientIdFromString(String patientId) {
		int pId=Integer.parseInt(patientId);
		this.patientId = pId;
	}

//	public Slot getSlot() {
//		return slot;
//	}
//
//	public void setSlot(Slot slot) {
//		this.slot = slot;
//	}
//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
}
