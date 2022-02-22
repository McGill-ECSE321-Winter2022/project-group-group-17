package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Person {
	@Id
	@GeneratedValue
	private int personID;

    private String email;
	
	// Person Associations
	@ManyToOne()
	@JoinColumn(name="addressid")
	private Address person_address;
	
	@OneToMany(mappedBy = "order_person", cascade = CascadeType.ALL)
	private List<Order> orders;
	// maybe add one for superclass?
	
	private String firstName;
	private String lastName;
	
	private String password;
	
	
	public Person() {} ;
	
	public Person(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Person(String firstName, String lastName, String email, String password, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.person_address = address;
	}

	public int getPersonID() {
		return this.personID;
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
