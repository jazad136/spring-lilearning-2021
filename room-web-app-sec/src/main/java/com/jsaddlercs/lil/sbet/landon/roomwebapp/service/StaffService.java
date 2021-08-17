package com.jsaddlercs.lil.sbet.landon.roomwebapp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.data.StaffRepository;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.StaffMember;

@Service
public class StaffService {

	private final StaffRepository staff;
	
	
	public StaffService(StaffRepository staff) { this.staff = staff; }


	public List<StaffMember> getAllStaff() { 
		return staff.findAll();
	}
}