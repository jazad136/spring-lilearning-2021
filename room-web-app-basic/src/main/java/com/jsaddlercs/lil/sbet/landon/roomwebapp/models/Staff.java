package com.jsaddlercs.lil.sbet.landon.roomwebapp.models;

public class Staff {
	
	private String id;
	private String lastName;
	private String firstName;
	private String position;
	public Staff() {
		
	}
	
	public Staff(String id, String lastName, String firstName, String position) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.position = position;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
