package com.boot.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.login.entity.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/adminHome")
	public String userHome()
	{
		return "admin";
	}
	@GetMapping("/enrollDoctor")
	public String enrollDoctor(Model model)
	{
		User user=new User();
		model.addAttribute("user", user);
		return "enrollDoctor";
	}
	@GetMapping("/enrollManager")
	public String enrollManager(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "enrollManager";
	}
	@GetMapping("/enrollDocMan")
	public String enrollDocMan(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "enrollDoc";
	}
	@GetMapping("/enrollMan")
	public String enrollMan(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "enrollMan";
	}

}
