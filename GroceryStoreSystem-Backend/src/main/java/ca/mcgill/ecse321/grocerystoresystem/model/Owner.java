package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Owner extends Person {

	public Owner() {};
	
	public Owner(String first_name, String last_name, String email, String password, boolean loginStatus) {
		super(first_name, last_name, email, password, loginStatus);
	}

	public Owner(String first_name, String last_name, String email, String password, Address address, boolean loginStatus) {
		super(first_name, last_name, email, password, address, loginStatus);
	}
}
