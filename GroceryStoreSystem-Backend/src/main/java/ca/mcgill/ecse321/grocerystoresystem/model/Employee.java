package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Employee extends Person {

  // Employee Associations
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private Shift shifts;
  
	public Employee(String first_name, String last_name, String email, String username, String password) {
		super(first_name, last_name, email, username, password);
	}

}
