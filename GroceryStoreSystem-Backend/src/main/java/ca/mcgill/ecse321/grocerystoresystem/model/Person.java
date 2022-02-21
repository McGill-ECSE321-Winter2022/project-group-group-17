package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public abstract class Person {
	@Id
	@GeneratedValue
	private int personID;
	
	@Column(unique=true)
    private String email;
	
	// Person Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person")
	private Address address;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Order> orders;
	// maybe add one for superclass?
	
	private String firstName;
	private String lastName;
	
	private String password;
	
	
	public Person(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
