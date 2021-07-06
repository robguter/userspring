package com.sisterag.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sisterag.springapp.entity.User;
import com.sisterag.springapp.repository.RoleRepository;
import com.sisterag.springapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService usrSrvc;

	@Autowired
	RoleRepository roleReps;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/userform")
	public String userform(Model mdl) {
		mdl.addAttribute("userForm", new User());
		mdl.addAttribute("userList", usrSrvc.getAllUsers());
		mdl.addAttribute("roles", roleReps.findAll());
		mdl.addAttribute("listTab", "active");
		return "user-form/user-view";
	}

}
