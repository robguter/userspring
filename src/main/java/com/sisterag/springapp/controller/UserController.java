package com.sisterag.springapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@GetMapping("/userForm")
	public String userForm(Model mdl) {
		mdl.addAttribute("userForm", new User());
		mdl.addAttribute("userList", usrSrvc.getAllUsers());
		mdl.addAttribute("roles", roleReps.findAll());
		mdl.addAttribute("listTab", "active");
		return "user-form/user-view";
	}
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
		}else {
			try {
				usrSrvc.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", usrSrvc.getAllUsers());
				model.addAttribute("roles",roleReps.findAll());
			}
		}
		model.addAttribute("userList", usrSrvc.getAllUsers());
		model.addAttribute("roles",roleReps.findAll());
		return "user-form/user-view";
	}
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model mdl, @PathVariable(name="id")Long id) throws Exception {
		User userToEdit = usrSrvc.getUserById(id);

		mdl.addAttribute("userForm", userToEdit);
		mdl.addAttribute("userList", usrSrvc.getAllUsers());
		mdl.addAttribute("roles", roleReps.findAll());
		mdl.addAttribute("formTab", "active");
		mdl.addAttribute("editMode", "true");
		return "user-form/user-view";
	}

	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode", "true");
		}else {
			try {
				usrSrvc.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", usrSrvc.getAllUsers());
				model.addAttribute("roles",roleReps.findAll());
				model.addAttribute("editMode", "true");
			}
		}
		model.addAttribute("userList", usrSrvc.getAllUsers());
		model.addAttribute("roles",roleReps.findAll());
		return "user-form/user-view";
	}
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) throws Exception {
		try {
			usrSrvc.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return userForm(model);
	}
}