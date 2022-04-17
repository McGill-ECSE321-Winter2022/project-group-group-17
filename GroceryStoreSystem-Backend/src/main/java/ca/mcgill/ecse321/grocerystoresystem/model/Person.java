package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Person {
	@Id
	@GeneratedValue
	private int personID;

    private String email;
	
//	// Person Associations
	@ManyToOne
	private Address person_address;
//
	@OneToMany(targetEntity = Order.class, fetch = FetchType.EAGER)
	private List<Order> orders;
	
	private String firstName;
	private String lastName;
	
	private String password;
	private boolean loginStatus;
	
	
	public Person() {} ;
	
	public Person(String firstName, String lastName, String email, String password, boolean loginStatus) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
	}
	
	public Person(String firstName, String lastName, String email, String password, Address address, boolean loginStatus) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.person_address = address;
		this.loginStatus = loginStatus;
	}

	public int getPersonID() {
		return this.personID;
	}

	public void setPersonID(int personID) { this.personID = personID; }
	
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

	public Address getAddress() {
		return this.person_address;
	}

	public void setAddress(Address address) {
		this.person_address = address;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public boolean getLoginStatus() {
		return this.loginStatus;
	}

	public List<Order> getOrders() {
		return orders;
	}
}