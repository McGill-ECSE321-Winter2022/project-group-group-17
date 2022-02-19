package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class Customer extends Person {

<<<<<<< Updated upstream
	public Customer(String firstName, String lastName, String email, String username, String password) {
		super(firstName, lastName, email, username, password);
=======
	public Customer(String first_name, String last_name, String email, String password) {
		super(first_name, last_name, email, password);
>>>>>>> Stashed changes
	}

}
