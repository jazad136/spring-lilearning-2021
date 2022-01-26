package com.example.ec.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "security_user")
public class User {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username") private String username;
	@Column(name="password") private String password;
	@Column(name="first_name") private String firstName;
	@Column(name="last_name") private String lastName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role"
	, joinColumns = @JoinColumn(name="user_id", referencedColumnName="id")
	, inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
	)
	
	private List<Role> roles;
	

	public User(String username, String password, Role role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.roles = Arrays.asList(role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public List<Role> getRoles() { return roles; }
	public void setRoles(List<Role> roles) { this.roles = roles; }
	
}
