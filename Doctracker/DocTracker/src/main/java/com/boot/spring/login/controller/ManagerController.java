package com.boot.spring.login.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.login.entity.Appointment;
import com.boot.spring.login.entity.Manager;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;
import com.boot.spring.login.entity.temp;
import com.boot.spring.login.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    static int id;
    static int docId;
    @Autowired
	private ManagerService managerService; 
	@GetMapping("/managerHome")
	public String managerHome(Principal principal,Model model)
	{
		String name=principal.getName();
		id=managerService.getManId(name);
		Manager man=managerService.getMan(id);
		docId=man.getDocId();
		System.out.println(docId);
		return "managerHome";
	}
	@GetMapping("/createSlot")
	public String createSlot(Principal principal,Model model)
	{
		Manager man=managerService.getMan(id);
		Slot slot=new Slot();
		model.addAttribute("manager", man);
		model.addAttribute("slot", slot);
		model.addAttribute("docId", docId);
		return "createSlot";
	
	}
	@GetMapping("/viewSlot")
	public String viewSlot(Model model)
	{
		//model.addAttribute("slot", slot);
		model.addAttribute("listSlot",managerService.getAllSlot(docId));
		model.addAttribute("docId", docId);
		return "viewSlot";
	}
	
	@PostMapping("/saveSlot")
	public String saveSlot(@ModelAttribute("slot") Slot slot)
	{ 
		
		managerService.saveSlot(slot);
		return "redirect:/manager/viewSlot";
	}
	
	@GetMapping("/updateSlot/{id}")
	public String updateSlot(@PathVariable(value="id") int id,Model model)
	{
		model.addAttribute("slot",managerService.getUpdateSlot(id));
		model.addAttribute("docId", docId);
		return "updateSlot";
	}
	@GetMapping("/deleteSlot/{id}")
	public String deleteSlot(@PathVariable(value="id") int id,Model model)
	{
		managerService.deleteSlotById(id);
		return "redirect:/manager/viewSlot";
	}
	@GetMapping("/expandSlot/{id}")
	public String expandSlot(@PathVariable(value="id") int id,Model model)
	{
		List<Appointment> appointments=managerService.getAppointments(id);
		List<temp> temps = new LinkedList<temp>();
		for (Appointment a:appointments){
			temp t=new temp();
			t.setAppointment(a);
		    t.setUser(managerService.getPatient(a.getPatientId()));
			temps.add(t);
		}
	    model.addAttribute("temps", temps);
		return "expandSlotManager";
	}


}
