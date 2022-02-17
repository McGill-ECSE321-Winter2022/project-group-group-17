package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public abstract class Person {
	@Id
	@GeneratedValue
	private int person_ID;
	
	// Person Associations
	@OneToOne(mappedBy = "person")
	private Address address;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Order orders;
	
	
	private String first_name;
	private String last_name;
	private String email;
	
	private String username;
	private String password;
	
	
	public Person(String first_name, String last_name, String email, String username, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPerson_ID() {
		return person_ID;
	}
}
