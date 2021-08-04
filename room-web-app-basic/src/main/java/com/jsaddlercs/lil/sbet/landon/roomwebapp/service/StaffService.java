package com.jsaddlercs.lil.sbet.landon.roomwebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.Staff;

@Service
public class StaffService {
	private static final List<Staff> staff = new ArrayList<>();
	static { 
		staff.add(new Staff(UUID.randomUUID().toString(), "John", "Doe", "Concierge"));
		staff.add(new Staff(UUID.randomUUID().toString(), "Jane", "Doe", "Front Desk"));
		staff.add(new Staff(UUID.randomUUID().toString(), "Oliver", "Handle", "Security"));
		staff.add(new Staff(UUID.randomUUID().toString(), "Sammy", "Smith", "Housekeeping"));
		
	}
	
	public List<Staff> getAllStaff() { 
		return staff;
	}
}
