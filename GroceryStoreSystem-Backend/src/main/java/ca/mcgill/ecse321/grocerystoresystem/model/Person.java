package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public abstract class Person {
	@Id
	@GeneratedValue
	@Column(unique=true)
    private String email;
	
	// Person Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn("person")
	private Address address;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Order orders;
	// maybe add one for superclass?
	
	private String firstName;
	private String lastName;
	
	
	
	private String username;
	private String password;
	
	
	public Person(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
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

//	public int getPerson_ID() {
//		return person_ID;
//	}
}
