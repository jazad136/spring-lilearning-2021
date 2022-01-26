package com.example.ec.web;

import javax.validation.constraints.NotNull;

/** Created by Mary Ellen Bowman, with syntactic sugar by Jonathan Saddler */
public class LoginDto {
	@NotNull private String username;
	@NotNull private String password;
	private String firstName;
	private String lastName;
	
	/** Default Constructor */
	protected LoginDto() { }

	public LoginDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; } 
	
	public String getFirstName() { return firstName; }
	
	public String getLastName() { return lastName; }
	
}
