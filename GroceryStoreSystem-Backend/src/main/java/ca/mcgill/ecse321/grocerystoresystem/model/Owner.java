package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Owner extends Person {

	public Owner() {};
	
	public Owner(String first_name, String last_name, String email, String password) {
		super(first_name, last_name, email, password);
	}

	public Owner(String first_name, String last_name, String email, String password, Address address) {
		super(first_name, last_name, email, password, address);
	}
}
