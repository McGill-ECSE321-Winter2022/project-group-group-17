package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class Customer extends Person {
	public Customer() {
		super(null, null, null, null, false);
		
	}
	
	public Customer(String first_name, String last_name, String email, String password, boolean loginStatus) {
		super(first_name, last_name, email, password, loginStatus);

	}
	
	public Customer(String first_name, String last_name, String email, String password, Address address, boolean loginStatus) {
		super(first_name, last_name, email, password, address, loginStatus);

	}

}
