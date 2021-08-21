package com.jsaddlercs.lil.sbet.landon.roomwebapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.StaffMember;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffRestController {
	private final StaffService staffService;
	public StaffRestController(StaffService staffService) { 
		this.staffService = staffService;
	}
	@GetMapping
	public List<StaffMember> getAllStaff() { 
		return staffService.getAllStaff();
	}
}
