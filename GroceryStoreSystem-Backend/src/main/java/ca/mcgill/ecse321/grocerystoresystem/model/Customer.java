package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class Customer extends Person {

	
	public Customer() {
		super(null, null, null, null);
		
	}
	
	public Customer(String first_name, String last_name, String email, String password) {
		super(first_name, last_name, email, password);

	}
	
	public Customer(String first_name, String last_name, String email, String password, Address address) {
		super(first_name, last_name, email, password, address);

	}

}
