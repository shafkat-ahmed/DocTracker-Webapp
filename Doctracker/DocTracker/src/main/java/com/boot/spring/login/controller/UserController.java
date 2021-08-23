package com.boot.spring.login.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.boot.spring.login.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.login.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	static int id;
	static Patient patient;
	@Autowired
	private UserService userService;
	
	@GetMapping("/userHome")
	public String userHome(Principal principal,Model theModel)
	{
		
		String name=principal.getName();
		System.out.println(principal);
		id=userService.getUserId(name);
		User user=userService.getUserById(id);
		String welcome="Welcome "+user.getFirstName();
		patient=userService.getPatient(id);
		List<String> templ=userService.getFieldList();
		HashSet<String> list = new HashSet<String>(templ);
		temp t=new temp();
		theModel.addAttribute("list",list);
		theModel.addAttribute("temp", t);
		theModel.addAttribute("id", id);
		theModel.addAttribute("welcome",welcome);
		return "user";
	}
	

	@GetMapping("/searchResult")
	public String genSearch(temp t, Model theModel) {
		System.out.println(t.getLoc());

		String field=t.getField();
		if (field==""){
			field=patient.getField();
		}
		System.out.println(field);
		
		
		if (t.getLoc()=="") {
			
			List<Doctor> templ=userService.findByField(field);
			//System.out.println(templ.size());
			String title= "Doctors from field "+field+":";
			theModel.addAttribute("title", title);
			theModel.addAttribute("list",templ);
//			System.out.println("-->"+templ.get(0).getUser().getFirstName());
			if(templ.size()==0){
				return "No result found";
			}
			return "findByField";
		}
		
		else {
			
			String loc=t.getLoc();
			String [] ar=loc.split("--");
			String title= "Doctors from field "+t.getField()+" around you:";
			theModel.addAttribute("title", title);
			
			float lat= Float.parseFloat(ar[0]);
			float lon= Float.parseFloat(ar[1]);
			
			
			float latUpper=(float) (lat+0.05);
			float latLower=(float) (lat-0.05);
			
			float lonUpper=(float) (lon+0.05);
			float lonLower=(float) (lon-0.05);
			
			List<Doctor> templ=userService.findByLoc(t.getField(),latLower, latUpper, lonLower, lonUpper);
			System.out.println(templ.size());

			double penalty=1;
			double diff=0.07;
			int size=templ.size();

			while (templ.size()<20){

				System.out.println(templ.size()+"--"+size+"--"+penalty+" "+diff);

				latUpper=(float) (lat+diff);
				latLower=(float) (lat-diff);

				lonUpper=(float) (lon+diff);
				lonLower=(float) (lon-diff);

				diff=diff+0.01;

				templ=userService.findByLoc(t.getField(),latLower, latUpper, lonLower, lonUpper);
				if (size==templ.size()){
					penalty=penalty*2.1;
				}
				else {
					penalty=penalty*1.2;
				}
				if (penalty>10000){
					break;
				}
			}

			if(templ.size()==0){
				System.out.println("test1");
				patient.setField(t.getField());
				theModel.addAttribute("temp", t);
				return "No result found";
			}
			theModel.addAttribute("list",templ);
			return "findByLocation";
			
		}	
	}


	@GetMapping("/availableSlots")
	public String availableSlots(temp t, Model model){


		int docId = Integer.parseInt(t.getDocId());

		System.out.println(id);
		System.out.println(patient.getUser().getFirstName());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		List <Slot> slots=userService.getSlots(docId,now);


		model.addAttribute("list",slots);



		return "availableSlots";
	}


	@GetMapping("/makeAppointment")
	public String makeAppointment(temp t, Model theModel){

		int slotId=Integer.parseInt(t.getSlotId());
		Appointment appointment=new Appointment();
		theModel.addAttribute("appointment",appointment);
		theModel.addAttribute("sId",slotId);
		theModel.addAttribute("pId",id);
		theModel.addAttribute("patient",patient);
		Slot slot=userService.getSlot(slotId);
		theModel.addAttribute("slot",slot);
		Doctor doctor=userService.getDoctor(slot.getDocId());
		theModel.addAttribute("doctor",doctor);

		return "makeAppointment";
	}

	@PostMapping("/saveAppointment")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {


		Slot slot=userService.getSlot(appointment.getSlotId());

		List<Appointment> list=userService.checkDuplicateSlotPick(slot.getId(),id);

		if (list.size()>0){
			return "warning";
		}

		userService.saveAppointment(appointment);
		userService.updatePatientCount(appointment.getSlotId(),slot.getPatientCount()-1);
		return "redirect:/user/viewAppointments";
	}

	@GetMapping("/warning")
	public String warning(){
		return "warning";
	}

	@GetMapping("/viewAppointments")
	public String viewAppointments(Model model){

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime now = LocalDateTime.now();

		List<Appointment> appointments=userService.getUserAppointments(id,now);

		List<temp> temps=new LinkedList<temp>();

		for (Appointment a:appointments){
			temp t=new temp();
			t.setAppointment(a);
			t.setSlot(userService.getSlot(a.getSlotId()));
			t.setPatient(userService.getPatient(a.getPatientId()));
			t.setDoctor(userService.getDoctor(t.getSlot().getDocId()));
			temps.add(t);
		}

		model.addAttribute("temps",temps);
		model.addAttribute("temp",new temp());
		return "viewAppointments";
	}
	
	@GetMapping("/viewPrevAppointments")
	public String viewPrevAppointments(Model model){

		List<Appointment> appointments=userService.getUserPrevAppointments(id);

		List<temp> temps=new LinkedList<temp>();

		for (Appointment a:appointments){
			temp t=new temp();
			t.setAppointment(a);
			t.setSlot(userService.getSlot(a.getSlotId()));
			t.setPatient(userService.getPatient(a.getPatientId()));
			t.setDoctor(userService.getDoctor(t.getSlot().getDocId()));
			temps.add(t);
		}

		model.addAttribute("temps",temps);
		model.addAttribute("temp",new temp());
		return "viewPrevAppointments";
	}

	@GetMapping("/dummy")
	public String dummy(temp t, Model model) {
		String s= "doc id is"+t.getDocId();
		model.addAttribute("s",s);
		return "dummy";
	}

	@GetMapping("/direction")
	public String direction(temp t, Model model) {
		int id=Integer.parseInt(t.getDocId());
		Doctor doctor=userService.getDoctor(id);
		String loc=t.getLoc();
		String [] ar=loc.split("--");
		float lat= Float.parseFloat(ar[0]);
		float lon= Float.parseFloat(ar[1]);

		model.addAttribute("doctor",doctor);
		model.addAttribute("curr_lat",lat);
		model.addAttribute("curr_lng",lon);

		return "direction";
	}

	@GetMapping("/showPrescription")
	public String showPrescription(temp t, Model model) {
		int id=t.getAppointment().getId();
		Slot slot=userService.getSlotByAppointmentId(id);
		Date date=slot.getDate();
		int docId=slot.getDocId();
		Doctor doctor= userService.getDoctor(docId);
		model.addAttribute("date", date);
		model.addAttribute("doctor", doctor);
		Appointment appointment=userService.getAppointmentById(id);
		model.addAttribute("appointment",appointment);
		model.addAttribute("t", t);
		model.addAttribute("prescription", appointment.getPrescription());
		return "showPrescription";
	}


	
}