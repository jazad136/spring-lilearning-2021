package com.jsaddlercs.lil.sbet.landon.roomwebapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class StaffMember {
	@Id
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String employeeId;
    
	@Column(name="FIRST_NAME") 
    private String firstName;
    
	@Column(name="LAST_NAME") 
    private String lastName;
    
	@Column(name="POSITION") 
	@Enumerated(EnumType.STRING)
    private Position position;
    
    public StaffMember() { }

	public StaffMember(String employeeId, String firstName, String lastName, Position position) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
    
	
}
