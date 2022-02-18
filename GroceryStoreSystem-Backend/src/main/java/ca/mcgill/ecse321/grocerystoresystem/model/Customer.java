package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Customer extends Person {

	public Customer(String firstName, String lastName, String email, String username, String password) {
		super(firstName, lastName, email, username, password);
	}

}
