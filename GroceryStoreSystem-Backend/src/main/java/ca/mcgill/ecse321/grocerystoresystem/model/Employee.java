package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee extends Person {

	// Employee Associations
	@OneToMany(targetEntity = Shift.class, fetch = FetchType.EAGER)
	private List<Shift> shifts;

	private EmployeeStatus empStatus;

	public Employee() {
		super(null, null, null, null, false);
	};

	public Employee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus, boolean loginStatus) {
		super(first_name, last_name, email, password, loginStatus);
		this.empStatus = empStatus;
	}
	public Employee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus, Address address, boolean loginStatus) {
		super(first_name, last_name, email, password, address, loginStatus);
		this.empStatus = empStatus;

	}

	public EmployeeStatus getEmpStatus() {
		return empStatus;
	}
	public boolean setEmpStatus(EmployeeStatus empStatus) {
		this.empStatus = empStatus;
		return (this.empStatus == empStatus);
	}
}
