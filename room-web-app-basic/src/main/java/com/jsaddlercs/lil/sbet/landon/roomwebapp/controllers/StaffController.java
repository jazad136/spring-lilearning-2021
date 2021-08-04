package com.jsaddlercs.lil.sbet.landon.roomwebapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.Staff;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	private static final List<Staff> staffs = new ArrayList<>();
	static { 
		staffs.add(new Staff(UUID.randomUUID().toString(), "John", "Doe", "Concierge"));
		staffs.add(new Staff(UUID.randomUUID().toString(), "Jane", "Doe", "Front Desk"));
		staffs.add(new Staff(UUID.randomUUID().toString(), "Oliver", "Handle", "Security"));
		staffs.add(new Staff(UUID.randomUUID().toString(), "Sammy", "Smith", "Housekeeping"));
		
	}
	public StaffController() { }
	@GetMapping
	public String getAllStaff(Model model) { 
		model.addAttribute("staffs", staffs);
		return "staff";
	}
}
