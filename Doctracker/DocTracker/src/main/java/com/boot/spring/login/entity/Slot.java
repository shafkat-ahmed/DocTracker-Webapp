package com.boot.spring.login.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="slot")
public class Slot {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@Column(name="doc_id")
	private int docId;
	
	@Column(name="date")
	private Date date;
	@Column(name="start_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm")
	private LocalDateTime startTime;
	@Column(name="end_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm")
	private LocalDateTime endTime;
	@Column(name="patient_count")
	private int patientCount;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime  startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime  getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime  endTime) {
		this.endTime = endTime;
	}
	public int getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}

	public String getStTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime stt=startTime;
		String st=dtf.format(stt).substring(10);

		return st;
	}
	public String getEdTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ett=endTime;
		String et=dtf.format(ett).substring(10);
		System.out.println(endTime);
		System.out.println(et);

		return et;
	}
}
