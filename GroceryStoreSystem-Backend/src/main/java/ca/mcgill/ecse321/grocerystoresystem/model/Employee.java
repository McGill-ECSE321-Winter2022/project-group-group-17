package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Employee extends Person {

  // Employee Associations
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private Shift shifts;
  private EmployeeStatus empStatus;
  
	public Employee(String first_name, String last_name, String email, String username, String password, EmployeeStatus empStatus ) {
		super(first_name, last_name, email, username, password);
		this.empStatus = empStatus;
	}
	public EmployeeStatus getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(EmployeeStatus empStatus) {
		this.empStatus = empStatus;
	}

}
