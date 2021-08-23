package com.boot.spring.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.spring.login.entity.Manager;
import com.boot.spring.login.entity.Patient;
import com.boot.spring.login.entity.User;
import com.boot.spring.login.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	static int docId;
	static int ManId;
	
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	@GetMapping("/success")
	public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        
        String role =  authResult.getAuthorities().toString();
        
        if(role.contains("ROLE_ADMIN")){
         response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/adminHome"));                            
         }
         else if(role.contains("ROLE_USER")) {
             response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user/userHome"));
         }
         else if(role.contains("ROLE_MANAGER")) {
             response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/manager/managerHome"));
         }
         else if(role.contains("ROLE_DOCTOR")) {
             response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/doctor/doctorHome"));
         }
        
    }
	
	@GetMapping("/signUp")
	public String signUp(Model model)
	{
		User user =new User();
		model.addAttribute("user",user);
		return "signUp";
	}
	@GetMapping("/signUpError")
	public String signUpError(Model model)
	{
		User user =new User();
		model.addAttribute("user",user);
		return "signUpError";
	}
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user,Model model)
	{
		int id=user.getId();
		List<User> users= userService.checkUnique(user.getUserName());
		if (users.size()>0){
			if(users.get(0).getId()!=id) {
				model.addAttribute("agerUser", user);
				return "signUpError";
			}
		}
		Patient patient = new Patient(0, 0);
		user.setPatient(patient);
		userService.saveUser(user);
		return "redirect:/user/userHome";

	}
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user)
	{
		int id=user.getId();
		String fn=user.getFirstName();
		String ln=user.getLastName();
		String email=user.getUserName();
		String pass=user.getPassword();
		userService.updateUser(fn,ln,email,pass,id);
		//userService.saveUser(user);
		return "redirect:/user/userHome";
		
	}
	@PostMapping("/saveDoctor")
	public String saveDoctor(@ModelAttribute("user") User user,Model model)
	{
		userService.saveUser(user);
		docId=user.getDoctor().getId();
		user = new User();
		model.addAttribute("user", user);
		model.addAttribute("docId", docId);
		System.out.println(docId);
		return "enrollMan";
	}
	@PostMapping("/saveManager")
	public String saveManager(@ModelAttribute("user") User user)
	{
		userService.saveUser(user);
		return "redirect:/admin/adminHome";
	}
	@GetMapping("/updateUser")
	public String updateUser(Model model)
	{
		int id=UserController.id;
		User user=userService.getUserById(id);
		model.addAttribute("user", user);
		return "updateUser";
	}

}