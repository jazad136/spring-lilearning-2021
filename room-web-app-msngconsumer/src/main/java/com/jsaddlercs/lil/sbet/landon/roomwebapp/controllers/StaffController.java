package com.jsaddlercs.lil.sbet.landon.roomwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	private final StaffService staffService;
	public StaffController(StaffService staffService) { 
		this.staffService = staffService;
	}
	@GetMapping
	public String getAllStaff(Model model) { 
		model.addAttribute("staff", staffService.getAllStaff());
		return "staff";
	}
}
