package com.boot.spring.login.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Doctor;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.temp;
import com.boot.spring.login.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	static int id;
	static int docId;
	static Doctor doctor;
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/doctorHome")
	public String doctorHome(Principal principal)
	{
		String name=principal.getName();
		id=doctorService.getId(name);
		docId=doctorService.getDocId(id);
		doctor=doctorService.getDoctorById(docId);
		return "doctorHome";
	}
	
	@GetMapping("/docSlot")
	public String docSlot(Model model)
	{
		List<Slot> slot=doctorService.getDocSlot(docId);
		model.addAttribute("slot", slot);
		model.addAttribute("doctor", doctor);
		return "docSlot";
	}
	@GetMapping("/expandSlot/{slotId}")
	public String expandSlot(@PathVariable(value="slotId") int slotId,Model model)
	{
		List<Appointment> appointments=doctorService.getAppointments(slotId);
		List<temp> temps = new LinkedList<temp>();
		for (Appointment a:appointments){
			temp t=new temp();
			t.setAppointment(a);
		    t.setUser(doctorService.getPatient(a.getPatientId()));
			temps.add(t);
		}
		model.addAttribute("temps", temps);
		model.addAttribute("appointments", appointments);
		return "expandSlot";
		
	}
	
	@GetMapping("/providePres/{appId}")
	public String providePres(@PathVariable(value="appId") int appId,Model model)
	{
		Appointment appointment=doctorService.getAppointmentById(appId);
		model.addAttribute("appointment", appointment);
		return "providePres";
		
	}
	
	@PostMapping("/savePres")
	public String savePres(@ModelAttribute("appointment") Appointment appointment) {
		doctorService.saveAppointment(appointment);
		return "redirect:/doctor/docSlot";
	}
	
	
	
}
